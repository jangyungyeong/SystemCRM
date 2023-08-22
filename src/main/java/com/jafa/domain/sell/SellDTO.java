package com.jafa.domain.sell;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("selldto")
public class SellDTO {
	private String regdate; // 판매등록일
	private String customerName; // 구매고객
	private String customerGrade; // 구매고객의 등급
	private String staffName; // 판매직원
}
