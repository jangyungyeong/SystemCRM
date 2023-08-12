package com.jafa.repository.product;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.product.PdCategoryVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class PdCategoryRepositoryTest extends AppTest{

	@Autowired
	PdCategoryRepository pdCategoryRepository;
	
	@Test
	@Ignore
	public void testReadCategory() {
		List<PdCategoryVO> readCategory = pdCategoryRepository.readCategory(1L);
		log.info(readCategory);
	}

}
