package com.jafa.service.sell;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.AppTest;
import com.jafa.domain.Criteria;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellProduct;
import com.jafa.domain.sell.SellVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class SellServiceImplTest extends AppTest{

	@Autowired
	private SellService sellService;
	
	@Test
	@Ignore
	public void testList() {
		Criteria criteria = new Criteria();
		sellService.SellList(criteria).forEach(s->log.info(s));
	}
	
	@Test
	@Ignore
	public void testRegister() {
		SellVO vo = SellVO.builder()
				.cno(3L)
				.productId(11L)
				.amount(4)
				.staffId("gang")
				.build();
		sellService.register(vo);
	}
	
	@Test
	@Ignore
	public void testGet() {
		SellDTO dto = sellService.get(1L);
		log.info(dto);
	}
	
	@Test
	@Ignore
	public void testModify() {
		SellVO sell = SellVO.builder()
				.sno(9L)
				.productId(2L)
				.amount(2)
				.build();
		sellService.modify(sell);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		sellService.remove(9L);
	}

	@Test
	@Ignore
	public void testTotalCount() {
		int totalCount = sellService.totalCount(new Criteria());
		log.info(totalCount);
	}
	
	@Test
	@Ignore
	public void testProductList() {
		sellService.ProductList(2L).forEach(p->log.info(p));
	}
}
