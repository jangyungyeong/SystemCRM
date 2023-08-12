package com.jafa.domain.customer;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("customer")
public class CustomerVO {
	private Long cno; // 번호
	private String customerName; // 고객이름
	private String sex; // 성별
	private String customerGrade; // 고객등급
	private String birthYear; // 생년월일_년도
	private String birthMonth; // 생년월일_월
	private String birthDate; // 생년월일_일
	private String firPhoneNum; // 첫번째 연락처
	private String midPhoneNum; // 가운데 연락처
	private String lastPhoneNum; // 마지막 연락처
	private String chargeStaff; // 담당직원
}
