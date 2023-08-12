package com.jafa.controller.customer;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomerControllerTest extends AppTest{

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
		ModelAndView modelAndView = mockMvc.perform(get("/customer/list"))
			.andReturn()
			.getModelAndView();
		Map<String, Object> model = modelAndView.getModel();
		log.info(model);
		log.info(modelAndView.getViewName());
	}

	@Test
	@Ignore
	public void testGet() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(get("/customer/get").param("cno", "1"))
				.andReturn()
				.getModelAndView();
			Map<String, Object> model = modelAndView.getModel();
			log.info(model);
			log.info(modelAndView.getViewName());
			assertEquals("customer/get", modelAndView.getViewName());
	}
	
	@Test
	@Ignore
	public void testModify() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(post("/customer/modify")
				.param("cno", "1")
				.param("BirthYear", "1981")
				.param("BirthMonth", "02")
				.param("BirthDate", "28")
				.param("FirPhoneNum", "010")
				.param("MidPhoneNum", "3333")
				.param("LastPhoneNum", "3337")
				.param("ChargeStaff", "김직원"))
		.andReturn()
		.getModelAndView();
		log.info(modelAndView.getViewName());
	}
	
	@Test
	@Ignore
	public void testRegister() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(post("/customer/register")
				.param("CustomerName", "코롱이")
				.param("sex", "여")
				.param("CustomerGrade", "신규")
				.param("BirthYear", "2010")
				.param("BirthMonth", "01")
				.param("BirthDate", "31")
				.param("FirPhoneNum", "010")
				.param("MidPhoneNum", "2344")
				.param("LastPhoneNum", "5521")
				.param("ChargeStaff", "장윤경"))
		.andReturn()
		.getModelAndView();
		log.info(modelAndView.getViewName());
	}
	
	@Test
	public void testRemove() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(post("/customer/remove")
				.param("cno", "5"))
			.andReturn()
			.getModelAndView();
			log.info(modelAndView.getViewName());
	}
}
