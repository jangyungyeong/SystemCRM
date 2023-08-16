package com.jafa.repository.staff;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.staff.AuthVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class AuthRepositoryTest extends AppTest{
	
	@Autowired
	AuthRepository authRepository;
	
	@Test
	@Ignore
	public void testInsert() {
		AuthVO vo = AuthVO.builder()
				.staffId("han")
				.auth("ROLE_STAFF")
				.build();
		authRepository.insert(vo);
		log.info(vo);
	}

}
