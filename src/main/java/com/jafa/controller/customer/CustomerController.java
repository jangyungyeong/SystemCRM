package com.jafa.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.Criteria;
import com.jafa.domain.Pagination;
import com.jafa.domain.customer.CustomerVO;
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
	
	@GetMapping({"/get","/modify"})
	public void get(Long cno, Model model, Criteria criteria) {
		model.addAttribute("customer",customerService.get(cno));
	}
	
	@PostMapping("/modify")
	public String modify(CustomerVO vo, RedirectAttributes rttr, Criteria criteria) {
		if (customerService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getCno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/customer/list"+criteria.getListLink();
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(CustomerVO vo, RedirectAttributes rttr) {
		customerService.register(vo);
		rttr.addFlashAttribute("result", vo.getCno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/customer/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long cno, RedirectAttributes rttr, Criteria criteria) {
		if (customerService.remove(cno)) {
			rttr.addFlashAttribute("result", cno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/customer/list"+criteria.getListLink();
	}
	
}
