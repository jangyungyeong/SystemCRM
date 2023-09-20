package com.jafa.controller.sell;

import java.net.URI;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.Criteria;
import com.jafa.domain.Pagination;
import com.jafa.domain.advice.AdviceVO;
import com.jafa.domain.sell.PdCategoryDTO;
import com.jafa.domain.sell.ProductVO;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellProduct;
import com.jafa.domain.sell.SellVO;
import com.jafa.repository.advice.AdviceRepository;
import com.jafa.service.customer.CustomerService;
import com.jafa.service.sell.SellService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class homeController {

	@Autowired
	SellService sellService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AdviceRepository adviceRepository;
	
	public homeController() {
		System.out.println("HomeController가 생성되었습니다.");
	}
	
	@GetMapping("/")
	public String home(Model model, Criteria criteria) {
		model.addAttribute("p", new Pagination(criteria, sellService.totalCount(criteria)));
		model.addAttribute("list", sellService.SellList(criteria));
		return "sell/home";
	}
	
	@GetMapping("/sell/get")
	public void get(Long cno, Model model, Criteria criteria) {
		model.addAttribute("sell", sellService.get(cno));
		model.addAttribute("product", sellService.ProductList(cno));
		model.addAttribute("customer", customerService.get(cno));
		model.addAttribute("advice", adviceRepository.read(cno));
		
//		sellService.getPdcategoryList().forEach(c-> log.info(c));
		model.addAttribute("pdCategory", sellService.getPdcategoryList(0));
		model.addAttribute("productList", sellService.getPdList(5));
		
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/sell/modify")
	public String modify(Long cno, Model model, Criteria criteria, Authentication auth) throws AccessDeniedException{
		SellDTO vo = sellService.get(cno);
		String staffname = auth.getName();
		if (!vo.getStaffId().equals(staffname) && !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			return "accessError";
		}
		model.addAttribute("sell", vo);
		return "sell/modify";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username==#vo.staffId or hasRole('ROLE_MANAGER')")
	@PostMapping("/sell/modify")
	public String modify(SellVO vo, RedirectAttributes rttr, Criteria criteria) {
		if (sellService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getCno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/"+criteria.getListLink();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/sell/register")
	public void register() {}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/sell/register")
	public String register(SellVO vo, RedirectAttributes rttr) {
		sellService.register(vo);
		rttr.addFlashAttribute("result", vo.getCno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username==#staffId or hasRole('ROLE_MANAGER')")
	@PostMapping("/sell/remove")
	public String remove(Long cno, RedirectAttributes rttr, Criteria criteria, String staffName) {
		if (sellService.remove(cno)) {
			rttr.addFlashAttribute("result", cno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/"+criteria.getListLink();
	}
	
	// 하위 카테고리 요청
	@GetMapping("/product/childCategory/{parentId}") 
	@ResponseBody
	public List<PdCategoryDTO> childCategory(@PathVariable Integer parentId){
		return sellService.getPdcategoryList(parentId);
	}
	
	// 제품리스트 요청
	@GetMapping("/product/productList/{categoryId}") 
	@ResponseBody
	public List<ProductVO> productList(@PathVariable Integer categoryId){
		return sellService.getPdList(categoryId);
	}
	
	// 제품정보 요청
	@GetMapping("/product/productInfo/{productId}") 
	@ResponseBody
	public ProductVO productInfo(@PathVariable Integer productId){
		return sellService.productInfo(productId);
	}
	
	// 판매변경모달창으로 구매정보넘김
	@GetMapping("/product/getEditProduct/{cno}")
	public ResponseEntity<List<SellProduct>> getEditProduct(@PathVariable Long cno){
		return new ResponseEntity<>(sellService.ProductList(cno),HttpStatus.OK); 
	}
	
	@PostMapping(value="/update/advice/{cno}", produces = "plain/text;charset=utf-8" )
	@ResponseBody
	public ResponseEntity<String> updateAdvice(@PathVariable Long cno, String editedContent){
		try {
            AdviceVO advice = adviceRepository.read(cno);
            if (advice == null) {
                return ResponseEntity.notFound().build();
            }

            advice.setContent(editedContent);
            adviceRepository.update(advice);
            
            return ResponseEntity.ok("상담이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 오류가 발생했습니다.");
        }
	}
	
	@DeleteMapping(value="/remove/advice/{cno}", produces = "plain/text;charset=utf-8" )
	@ResponseBody
	public ResponseEntity<String> dropAdvice(@PathVariable Long cno, HttpSession session){
		
		try {
			AdviceVO advice = adviceRepository.read(cno);
			if (advice == null) {
				return ResponseEntity.status(HttpStatus.SEE_OTHER)
						.location(URI.create("/update/advice/{cno}")).build();
			}
			
			adviceRepository.delete(cno);
			
		  // 세션 상담삭제상태 유지
          session.setAttribute("adviceDelete", true);
			
			return ResponseEntity.ok("상담이 성공적으로 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 오류가 발생했습니다.");
		}
	}
		
}
