package com.jafa.domain.sell;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Alias("product")
public class ProductVO {
	
	private Long productId; // 제품번호
	private Long categoryId; // 카테고리번호
	private String productName; // 제품이름
	private String productNumber; // 제품호수
	private int price; // 제품가격
}
