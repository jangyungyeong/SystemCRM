package com.jafa.controller.customer;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.Criteria;
import com.jafa.domain.Pagination;
import com.jafa.domain.customer.CustomerVO;
import com.jafa.domain.staff.StaffVO;
import com.jafa.service.customer.CustomerService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/customer")
@Log4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		model.addAttribute("p", new Pagination(criteria, customerService.totalCount(criteria)));
		model.addAttribute("list", customerService.customerList(criteria));
	}
	
	@GetMapping("/get")
	public void get(Long cno, Model model, Criteria criteria) {
		model.addAttribute("customer",customerService.get(cno));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public String modify(Long cno, Model model, Criteria criteria, Authentication auth) throws AccessDeniedException {
		CustomerVO vo = customerService.get(cno);
		String staffname = auth.getName();
		if (!vo.getChargeStaff().equals(staffname) &&
				!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			throw new AccessDeniedException("Access denided");
		}
		model.addAttribute("customer", vo);
		return "customer/modify";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username==#vo.chargeStaff or hasRole('ROLE_MANAGER')")
	@PostMapping("/modify")
	public String modify(CustomerVO vo, RedirectAttributes rttr, Criteria criteria) {
		if (customerService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getCno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/customer/list"+criteria.getListLink();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(CustomerVO vo, RedirectAttributes rttr) {
		customerService.register(vo);
		rttr.addFlashAttribute("result", vo.getCno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/customer/list";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username==#vo.chargeStaff or hasRole('ROLE_MANAGER')")
	@PostMapping("/remove")
	public String remove(Long cno, RedirectAttributes rttr, Criteria criteria) {
		if (customerService.remove(cno)) {
			rttr.addFlashAttribute("result", cno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/customer/list"+criteria.getListLink();
	}
	
}
