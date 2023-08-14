package com.jafa.domain.staff;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthVO implements Serializable{
	private static final long serialVersionUID = 1124343263615870945L;
	
	String staffId;
	String auth;
	
}
