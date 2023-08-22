package com.jafa.repository.advice;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.advice.AdviceVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class AdviceRepositoryTest extends AppTest{

	@Autowired
	AdviceRepository adviceRepository;
	
	@Test
	@Ignore
	public void testInsert() {
		AdviceVO vo = AdviceVO.builder()
				.sno(2L)
				.content("상담내용등록테스트")
				.cno(1L)
				.staffId("jang")
				.build();
		adviceRepository.insert(vo);
		System.out.println(vo);
	}
	
	@Test
	@Ignore
	public void testRead() {
		AdviceVO vo = adviceRepository.read(4L);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		AdviceVO vo = AdviceVO.builder()
				.vno(2L)
				.content("상담수정 테스트")
				.build();
		adviceRepository.update(vo);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		adviceRepository.delete(6L);
	}

}
