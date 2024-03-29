package com.jafa.service.staff;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.staff.AuthVO;
import com.jafa.domain.staff.StaffVO;
import com.jafa.repository.staff.AuthRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffServiceImplTest extends AppTest{

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Test
	@Ignore
	public void testJoinManager() {
		StaffVO vo = StaffVO.builder()
				.staffId("jang")
				.staffPwd("1234")
				.staffName("장윤경")
				.staffEmail("jang@test.com")
				.staFirPhoneNum("010")
				.staMidPhoneNum("4907")
				.staLastPhoneNum("6646")
				.staffRank("매니저")
				.build();
		staffService.join(vo);
		
//		AuthVO authVO = new AuthVO("jang","ROLE_MANAGER");
//		authRepository.insert(authVO);
	}
	
	@Test
	@Ignore
	public void testJoinStaff() {
		StaffVO vo = StaffVO.builder()
				.staffId("han")
				.staffPwd("1234")
				.staffName("한남자")
				.staffEmail("han@test.com")
				.staFirPhoneNum("010")
				.staMidPhoneNum("6322")
				.staLastPhoneNum("1111")
				.staffRank("사원")
				.build();
		staffService.join(vo);
	}
	
}
