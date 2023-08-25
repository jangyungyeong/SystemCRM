package com.jafa.service.sell;

import java.util.List;

import com.jafa.domain.Criteria;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellProduct;
import com.jafa.domain.sell.SellVO;

public interface SellService {

	List<SellDTO> SellList(Criteria criteria); // 판매목록
	
	void register(SellVO sell); // 판매등록
	
	SellDTO get(Long cno); // 판매조회
	
	boolean modify(SellVO sell); // 판매수정
	
	boolean remove(Long cno); // 판매삭제
	
	int totalCount(Criteria criteria); // 전체게시물 수
	
	List<SellProduct> ProductList(Long cno); // 고객구매상품리스트
}
