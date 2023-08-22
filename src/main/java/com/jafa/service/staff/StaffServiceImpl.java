package com.jafa.service.staff;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.domain.staff.AuthVO;
import com.jafa.domain.staff.StaffVO;
import com.jafa.exception.PasswordMisMatchException;
import com.jafa.repository.staff.AuthRepository;
import com.jafa.repository.staff.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void join(StaffVO vo) {
		vo.setStaffPwd(passwordEncoder.encode(vo.getStaffPwd()));
		AuthVO authVO = new AuthVO(vo.getStaffId(),"ROLE_MANAGER");
		staffRepository.insert(vo);
		authRepository.insert(authVO);
	}

	@Override
	public void modify(StaffVO vo) {
		staffRepository.update(vo);
	}

	@Override
	public StaffVO read(String staffId) {
		return staffRepository.selectById(staffId);
	}

	@Transactional
	@Override
	public void changePassword(Map<String, String> staffMap) {
		String staffId = staffMap.get("staffId");
		String newPwd = staffMap.get("newPwd"); // 새로운 비밀번호
		String currentPwd = staffMap.get("currentPwd"); // 현재 비밀번호
		StaffVO vo = staffRepository.selectById(staffId);
		
		if (!passwordEncoder.matches(currentPwd, vo.getStaffPwd())) {
			throw new PasswordMisMatchException();
		}
		staffRepository.updatePassword(staffId, passwordEncoder.encode(newPwd));
		
	}

}
