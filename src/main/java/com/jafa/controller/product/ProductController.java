package com.jafa.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jafa.domain.product.PdCategoryVO;
import com.jafa.domain.product.ProductDTO;
import com.jafa.domain.product.ProductVO;
import com.jafa.repository.product.PdCategoryRepository;
import com.jafa.repository.product.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	PdCategoryRepository pdCategoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	// 상위 카테고리 요청
	@RequestMapping("/list")
	public String list(Model model) {
		List<PdCategoryVO> category = pdCategoryRepository.readCategory(0L);
		model.addAttribute("category",category);
		return "product/list";
	}
	
	// 하위 카테고리 요청
	@GetMapping("/child/{parentcategoryId}")
	@ResponseBody
	public List<PdCategoryVO> childCategory(@PathVariable Long parentcategoryId){
		return pdCategoryRepository.readCategory(parentcategoryId);
	}
	
	// 제품리스트 요청
	@GetMapping("/productList/{categoryId}")
	@ResponseBody
	public List<ProductVO> productList(@PathVariable Long categoryId){
		return productRepository.list(categoryId);
	}
	
	// 제품정보 요청
	@GetMapping("/productInfo/{productId}")
	@ResponseBody
	public ProductDTO productInfo(@PathVariable Long productId) {
		return productRepository.productInfo(productId);
	}
	
}
