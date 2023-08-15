package com.jafa.repository.staff;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.staff.StaffVO;

public interface StaffRepository {
	
	StaffVO read(String staffId); // 직원조회
	
	void insert(StaffVO vo); // 직원 등록
	
	void update(StaffVO vo); // 직원정보 수정
	
	StaffVO selectById(String staffId); // 아이디로 회원정보 검색
	
	String selectByEmail(String staffEmail); // 이메일로 아이디 검색
	
	void updatePassword( // 비밀번호 변경
		@Param("staffId") String staffId,
		@Param("staffPwd") String staffPwd);
	
}
