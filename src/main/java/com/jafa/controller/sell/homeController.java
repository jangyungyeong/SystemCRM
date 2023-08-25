package com.jafa.controller.sell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.Criteria;
import com.jafa.domain.Pagination;
import com.jafa.domain.advice.AdviceVO;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellVO;
import com.jafa.repository.advice.AdviceRepository;
import com.jafa.service.customer.CustomerService;
import com.jafa.service.sell.SellService;

@Controller
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
		
}
