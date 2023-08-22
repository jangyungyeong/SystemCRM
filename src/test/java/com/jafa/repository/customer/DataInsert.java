package com.jafa.repository.customer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.customer.CustomerVO;

public class DataInsert extends AppTest{

	@Autowired
	CustomerRepository repository;
	
	@Test
	public void test() {
		for(int i=1;i<=212;i++) {
			CustomerVO vo = CustomerVO.builder()
					.customerName("김자바"+i)
					.sex("여"+(i%5))
					.customerGrade("신규"+ (i%5))
					.birthYear("yyyy")
					.birthMonth("mm")
					.birthDate("dd")
					.firPhoneNum("010")
					.midPhoneNum("1111")
					.lastPhoneNum("1111")
					.chargeStaff("장윤경"+ (i%5))
					.build();
			repository.insert(vo);			
		}
		for(int i=1;i<=212;i++) {
			CustomerVO vo = CustomerVO.builder()
					.customerName("감자바"+i)
					.sex("남"+(i%5))
					.customerGrade("일반"+ (i%5))
					.birthYear("yyyy")
					.birthMonth("mm")
					.birthDate("dd")
					.firPhoneNum("010")
					.midPhoneNum("2222")
					.lastPhoneNum("2222")
					.chargeStaff("유능한"+ (i%5))
					.build();
			repository.insert(vo);			
		}
		for(int i=1;i<=212;i++) {
			CustomerVO vo = CustomerVO.builder()
					.customerName("김진상"+i)
					.sex("여"+(i%5))
					.customerGrade("우수"+ (i%5))
					.birthYear("yyyy")
					.birthMonth("mm")
					.birthDate("dd")
					.firPhoneNum("010")
					.midPhoneNum("3333")
					.lastPhoneNum("3333")
					.chargeStaff("막사라"+ (i%5))
					.build();
			repository.insert(vo);			
		}
		for(int i=1;i<=212;i++) {
			CustomerVO vo = CustomerVO.builder()
					.customerName("나예뻐"+i)
					.sex("남"+(i%5))
					.customerGrade("신규"+ (i%5))
					.birthYear("yyyy")
					.birthMonth("mm")
					.birthDate("dd")
					.firPhoneNum("010")
					.midPhoneNum("4444")
					.lastPhoneNum("4444")
					.chargeStaff("다온나"+ (i%5))
					.build();
			repository.insert(vo);			
		}
		
	}
}
