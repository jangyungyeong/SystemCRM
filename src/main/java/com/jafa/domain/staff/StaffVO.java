package com.jafa.domain.staff;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StaffVO implements Serializable{
	
	private static final long serialVersionUID = -1161300462847277536L;
	
	private String staffId; // 직원아이디
	private String staffPwd; // 직원비밀번호
	private String StaffName; // 직원이름
	private String staffEmail; // 직원이메일
	private String staFirPhoneNum; // 첫번째연락처
	private String staMidPhoneNum; // 가운데연락처
	private String staLastPhoneNum; // 마지막연락처
	private String staffRank; // 직급
	private boolean enabled;
	
	private LocalDateTime regDate; 
	private LocalDateTime updateDate; 
	
	private List<AuthVO> authList; 
}
