package com.jafa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jafa.domain.staff.StaffVO;
import com.jafa.repository.staff.StaffRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		StaffVO vo = staffRepository.read(username);
		if (vo==null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUser(vo);
	}

}
