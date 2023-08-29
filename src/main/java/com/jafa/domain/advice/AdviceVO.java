package com.jafa.domain.advice;

import org.apache.ibatis.type.Alias;

import com.jafa.domain.sell.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("advice")
public class AdviceVO {
	private Long vno; // 상담번호
	private Long sno; // 판매번호 
	private Long cno; // 고객번호
	private String staffId; // 직원아이디
	private String content; // 상담내용
}
