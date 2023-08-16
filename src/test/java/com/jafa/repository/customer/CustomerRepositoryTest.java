package com.jafa.repository.customer;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.Criteria;
import com.jafa.domain.customer.CustomerVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomerRepositoryTest extends AppTest{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	@Ignore
	public void test() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(1);
		List<CustomerVO> list = customerRepository.customerList(criteria);
		list.forEach(c->log.info(c));
	}
	
	@Test
	@Ignore
	public void testInsertSelectKey() {
		CustomerVO vo = CustomerVO.builder()
				.customerName("신짱구")
				.sex("남")
				.customerGrade("신규")
				.birthYear("1997")
				.birthMonth("8")
				.birthDate("25")
				.firPhoneNum("010")
				.midPhoneNum("2255")
				.lastPhoneNum("4444")
				.chargeStaff("장윤경")
				.build();
		customerRepository.insertSelectKey(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testRead() {
		CustomerVO read = customerRepository.read(2L);
		log.info(read);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		CustomerVO vo = CustomerVO.builder()
				.cno(1L)
				.birthYear("2005")
				.birthMonth("08")
				.birthDate("11")
				.firPhoneNum("011")
				.midPhoneNum("9999")
				.lastPhoneNum("1111")
				.chargeStaff("김뚜꽁")
				.build();
		int count = customerRepository.update(vo);
		log.info("업데이트 된 행의 개수 : " + count);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		int count = customerRepository.delete(5L);
		log.info("삭제 된 행의 개수 : " + count);
	}
	
	@Test
	@Ignore
	public void testGetTotalCount() {
		log.info(customerRepository.getTotalCount(new Criteria()));
	}
	
	@Test
//	@Ignore
	public void testSearch() {
		Criteria criteria = new Criteria();
		criteria.setType("S");
		criteria.setKeyword("장윤경");
		customerRepository.customerList(criteria);
		log.info(customerRepository.getTotalCount(criteria));
	}

}
