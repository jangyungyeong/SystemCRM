package com.jafa.repository.advice;

import com.jafa.domain.advice.AdviceVO;
import com.jafa.domain.sell.SellVO;
import com.jafa.domain.staff.AuthVO;

public interface AdviceRepository {

	// 상담등록
	void insert(AdviceVO vo);
	
	// 상담조회
	AdviceVO read(Long cno);
	
	// 상담수정
	void update(AdviceVO vo);
	
	// 상담삭제
	void delete(Long vno);
	
}
