package com.jafa.repository.customer;

import java.util.List;

import com.jafa.domain.Criteria;
import com.jafa.domain.customer.CustomerVO;

public interface CustomerRepository {
	
	// 고객목록
	List<CustomerVO> customerList(Criteria criteria);
	
	// 고객정보 등록
	void insert(CustomerVO vo);
	
	// 등록된 행의 개수
	Integer insertSelectKey(CustomerVO vo);
	
	// 고객 조회
	CustomerVO read(Long cno);
	
	// 수정된 행의 개수
	int update(CustomerVO vo);
	
	// 삭제된 행의 개수
	int delete(Long cno);
	
	// 전체 게시물 수
	int getTotalCount(Criteria criteria);
	
}
