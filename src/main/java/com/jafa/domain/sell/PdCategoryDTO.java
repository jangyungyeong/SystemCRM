package com.jafa.domain.sell;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Alias("pdcategory")
public class PdCategoryDTO {
	private int level;
	private int parentCategoryId;
	private int categoryId; 
	private String categoryName;
}
