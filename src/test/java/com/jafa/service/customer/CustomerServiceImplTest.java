package com.jafa.service.customer;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.Criteria;
import com.jafa.domain.customer.CustomerVO;
import com.jafa.service.customer.CustomerService;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomerServiceImplTest extends AppTest{

	@Autowired
	CustomerService customerService;
	
	@Test
	@Ignore
	public void testCustomerList() {
		Criteria criteria = new Criteria();
		customerService.customerList(criteria).forEach(c->log.info(c));
	}
	
	@Test
	@Ignore
	public void testRegister() {
		CustomerVO vo = CustomerVO.builder()
				.customerName("짠돌이")
				.sex("남")
				.customerGrade("신규")
				.birthYear("1999")
				.birthMonth("07")
				.birthDate("12")
				.firPhoneNum("010")
				.midPhoneNum("9889")
				.lastPhoneNum("1234")
				.chargeStaff("장윤경")
				.build();
		customerService.register(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testGet() {
		CustomerVO vo = customerService.get(5L);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testModify() {
		CustomerVO vo = CustomerVO.builder()
				.cno(2L)
				.birthYear("2002")
				.birthMonth("05")
				.birthDate("05")
				.firPhoneNum("010")
				.midPhoneNum("5566")
				.lastPhoneNum("1314")
				.chargeStaff("장윤경")
				.build();
		customerService.modify(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testRemove() {
		customerService.remove(5L);
	}
	
	@Test
	@Ignore
	public void testTotalCount() {
		int totalCount = customerService.totalCount(new Criteria());
		log.info(totalCount);
	}

}
