package com.jafa.service.staff;

import java.util.Map;

import com.jafa.domain.staff.StaffVO;

public interface StaffService {
	
	void join(StaffVO vo); // 회원가입
	
	void modify(StaffVO vo); // 회원정보 수정
	
	StaffVO read(String staffId); // 회원조회
	
	void changePassword(Map<String, String> staffMap); // 비밀번호 변경
}
