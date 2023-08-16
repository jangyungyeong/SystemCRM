package com.jafa.repository.staff;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.staff.StaffVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class StaffRepositoryTest extends AppTest{

	@Autowired
	StaffRepository staffRepository;
	
	@Test
	@Ignore
	public void testRead() {
		StaffVO read = staffRepository.read("jang");
		log.info(read);
	}
	
	@Test
	@Ignore
	public void testInsert() {
		StaffVO vo = StaffVO.builder()
				.staffId("han")
				.staffPwd("4422")
				.staffName("한남자")
				.staffEmail("han@test.com")
				.staFirPhoneNum("010")
				.staMidPhoneNum("9999")
				.staLastPhoneNum("5757")
				.staffRank("사원")
				.build();
		staffRepository.insert(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		StaffVO vo = StaffVO.builder()
				.staffId("wang")
				.staffName("왕나봉")
				.staffEmail("wang@test.com")
				.staFirPhoneNum("010")
				.staMidPhoneNum("3215")
				.staLastPhoneNum("1234")
				.build();
		staffRepository.update(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testSelectById() {
		StaffVO select = staffRepository.selectById("jang");
		log.info(select);
	}
	
	@Test
	@Ignore
	public void testSelectByEmail() {
		String select = staffRepository.selectByEmail("jang@test.com");
		log.info(select);
	}
	
	@Test
	@Ignore
	public void testUpdatePassword() {
		staffRepository.updatePassword("wang", "4321");
	}
	
	

}
