package com.jafa.controller.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.jafa.AppTest;
import com.jafa.domain.product.PdCategoryVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ProductControllerTest extends AppTest{

	@Autowired
	WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	@Ignore
	public void testList() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(get("/product/list"))
				.andReturn()
				.getModelAndView();
		Map<String, Object> model = modelAndView.getModel();
		log.info(model);
		log.info(modelAndView.getViewName());
	}
	
}
