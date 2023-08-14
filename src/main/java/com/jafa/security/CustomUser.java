package com.jafa.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jafa.domain.staff.StaffVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User{

	private StaffVO staffVO;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(StaffVO staffVO) {
		super(staffVO.getStaffId(), staffVO.getStaffPwd(),
				staffVO.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
				this.staffVO = staffVO;
	}

}
