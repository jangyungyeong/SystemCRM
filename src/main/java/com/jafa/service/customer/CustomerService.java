package com.jafa.service.customer;

import java.util.List;

import com.jafa.domain.Criteria;
import com.jafa.domain.customer.CustomerVO;

public interface CustomerService {
	
	List<CustomerVO> customerList(Criteria criteria); // 고객 목록
	
	void register(CustomerVO customer); // 고객 등록
	
	CustomerVO get(Long cno); // 고객 조회
	
	boolean modify(CustomerVO customer); // 고객정보 수정
	
	boolean remove(Long cno); // 고객 삭제
	
	int totalCount(Criteria criteria); // 전체게시물 수
}
