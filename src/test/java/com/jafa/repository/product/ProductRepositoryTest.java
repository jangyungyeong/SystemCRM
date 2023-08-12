package com.jafa.repository.product;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.product.ProductDTO;
import com.jafa.domain.product.ProductVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ProductRepositoryTest extends AppTest{

	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Ignore
	public void testList() {
		List<ProductVO> list = productRepository.list(5L);
		log.info(list);
	}
	
	@Test
	@Ignore
	public void testProductInfo() {
		ProductDTO list = productRepository.productInfo(20L);
		log.info(list);
	}

}
