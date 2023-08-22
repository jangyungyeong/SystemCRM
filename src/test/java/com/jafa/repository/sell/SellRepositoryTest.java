package com.jafa.repository.sell;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class SellRepositoryTest extends AppTest{

	@Autowired
	private SellRepository sellRepository;
	
	@Test
	@Ignore
	public void testList() {
		List<SellDTO> list = sellRepository.sellList();
		list.forEach(s->log.info(s));
	}
	
	@Test
	@Ignore
	public void testInsert() {
		SellVO vo = SellVO.builder()
				.cno(1L)
				.productId(9L)
				.amount(1)
				.staffId("han")
				.build();
		sellRepository.insert(vo);
		log.info(vo);
	}
	
	@Test
//	@Ignore
	public void testInsertSelectKey() {
		SellVO vo = SellVO.builder()
				.cno(2L)
				.productId(12L)
				.amount(2)
				.staffId("wang")
				.build();
		sellRepository.insert(vo);
		log.info(vo);
	}
	
	@Test
//	@Ignore
	public void testRead() {
		SellVO vo = new SellVO();
		vo.setSno(8L);
		SellDTO read = sellRepository.read(vo);
		log.info(read);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		SellVO vo = SellVO.builder()
				.sno(9L)
				.productId(13L)
				.amount(3)
				.build();
		int count = sellRepository.update(vo);
		log.info(count);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		int count = sellRepository.delete(11L);
		log.info(count);
	}

}
