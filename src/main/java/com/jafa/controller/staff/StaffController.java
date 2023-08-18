package com.jafa.controller.staff;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.staff.StaffVO;
import com.jafa.exception.PasswordMisMatchException;
import com.jafa.service.staff.MailSendService;
import com.jafa.service.staff.NotFoundMemberException;
import com.jafa.service.staff.StaffService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class StaffController {

	@Autowired
	StaffService staffService;
	
	@Autowired
	MailSendService mailSendService;
	
	// 페이지 접근금지 403 에러처리
	@GetMapping("/accessDenied")
	public String accessDenided() {
		return "accessError";
	}
	
	// 약관 동의
	@GetMapping("/join/step1")
	public String step1() {
		return "staff/step1";
	}
	
	// 이메일 인증
	@PostMapping("/join/step2")
	public String step2(@RequestParam(defaultValue = "false") List<Boolean> agreement) {
		log.info(agreement);
		if(agreement.size()>=2 && agreement.stream().allMatch(s-> s)) {
			return "staff/step2";			
		}
		return "staff/step1";
	}
	
	// 인증번호 이메일 요청
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String staffEmail) {
		return mailSendService.joinEmail(staffEmail);
	}
	
	
	@PostMapping("/join/step3")
	public String joinForm(StaffVO staffVO) {
		return "staff/join";
	}
	
	@PostMapping("/staff/join")
	public String join(StaffVO staffVO, RedirectAttributes rttr) {
		staffService.join(staffVO);
		return "redirect:/";
	}
	
	// 로그인 페이지
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, Authentication auth, RedirectAttributes rttr) {
		String uri = request.getHeader("Referer"); // 로그인 전 사용자가 보던 페이지 
		if(uri!=null && !uri.contains("/login")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		log.info(uri);
		if(auth!=null) { // 이미 로그인 중 
			rttr.addFlashAttribute("duplicationLogin", "이미 로그인 중입니다.");
			if(uri==null) uri="/";
			return "redirect:"+uri;
		}
		return "staff/login";
	}
	
	// 아이디 중복 체크 
	@PostMapping("/staff/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> isDuplicateCheck(String staffId){
		StaffVO vo = staffService.read(staffId);
		return vo == null ? new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK) :
			new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
	}
	
	// 아이디 찾기 또는 임시비밀번호 발급 페이지
	@GetMapping("/findStaffInfo")
	public String findStaffInfo() {
		return "staff/findStaffInfo";
	}
	
	// 아이디 찾기 이메일 전송 
	@PostMapping(value = "/findStaffId", produces = "plain/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> findStaffId(String staffEmail){
		try {
			mailSendService.findIdByEmail(staffEmail);			
		} catch (NotFoundMemberException e) {
			return new ResponseEntity<String>("회원정보를 찾을 수 없습니다.",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("가입하신 이메일로 전송되었습니다.",HttpStatus.OK);
	}
	
	// 비밀번호 재발급 이메일 전송 
	@PostMapping(value = "/findStaffPwd", produces = "plain/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> findStaffPwd(String staffEmail){
		try {
			mailSendService.findPwdByEmail(staffEmail);			
		} catch (NotFoundMemberException e) {
			return new ResponseEntity<String>("회원정보를 찾을 수 없습니다.",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("가입하신 이메일로 전송되었습니다.",HttpStatus.OK);
	}
	
	// 비밀번호 변경 처리
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_STAFF')")
	@PostMapping(value = "/mypage/changePwd", produces = "plain/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> chnagePwd(@RequestParam Map<String, String> staffMap){
		
		try {
			staffService.changePassword(staffMap);
		} catch (PasswordMisMatchException e) {
			return new ResponseEntity<String>("비밀번호가 일치하지 않습니다.",HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("성공",HttpStatus.OK);
	}
	
	// 마이페이지 
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_STAFF')")
	@GetMapping({"/mypage","/mypage/{path}"})
	public String myPage(Principal principal, Model model, @PathVariable(required = false) String path) {
		String staffId = principal.getName();
		if(path==null) {
			StaffVO staffVO = staffService.read(staffId);
			model.addAttribute("vo", staffVO);			
			return "staff/mypage";
		}
		return "staff/" + path;
	}
	
	// 회원정보수정
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_STAFF')")
	@PostMapping("/staff/modify")
	public String modify(StaffVO staffVO, RedirectAttributes rttr) {
		staffService.modify(staffVO);
		rttr.addFlashAttribute("result", "modify");
		return "redirect:/mypage";
	}
	
}
