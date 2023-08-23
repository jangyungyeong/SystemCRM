package com.jafa.controller.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellVO;
import com.jafa.service.customer.CustomerService;
import com.jafa.service.sell.SellService;

@Controller
public class homeController {

	@Autowired
	SellService sellService;
	
	public homeController() {
		System.out.println("HomeController가 생성되었습니다.");
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("list", sellService.SellList());
		return "sell/home";
	}
	
	@GetMapping("/sell/get")
	public void get(Long cno, Model model) {
		model.addAttribute("sell", sellService.get(cno));
	}
	
	@GetMapping("/sell/modify")
	public String modify(Long cno, Model model) {
		SellDTO sell = sellService.get(cno);
		model.addAttribute("sell", sell);
		return "sell/modify";
	}
	
	@PostMapping("/sell/modify")
	public String modify(SellVO vo, RedirectAttributes rttr) {
		if (sellService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getCno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/";
	}
	
	@GetMapping("/sell/register")
	public void register() {}
	
	@PostMapping("/sell/register")
	public String register(SellVO vo, RedirectAttributes rttr) {
		sellService.register(vo);
		rttr.addFlashAttribute("result", vo.getCno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/";
	}
}
