package com.jafa.domain.product;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Alias("productdto")
public class ProductDTO {
	private Long productId; // 제품번호
	private String parentCategoryName; // 부모(최상위)카테고리 이름
	private String categoryName; // 하위 카테고리 이름
	private String productName; // 제품이름
	private String productNumber; // 제품호수
	private int price; // 가격
}
