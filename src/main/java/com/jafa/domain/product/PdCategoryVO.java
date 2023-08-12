package com.jafa.domain.product;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Alias("pdcategory")
public class PdCategoryVO {

	private Long categoryId; // 카테고리 번호
	private Long parentCategoryId; // 상위카테고리, 최상위 카테고리인 경우 parentCategoryId 값은 0
	private String categoryName; // 카테고리 이름
	
}
