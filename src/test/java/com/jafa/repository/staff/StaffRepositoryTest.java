package com.jafa.repository.staff;

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
	public void testRead() {
		StaffVO read = staffRepository.read("jang");
		log.info(read);
	}

}
