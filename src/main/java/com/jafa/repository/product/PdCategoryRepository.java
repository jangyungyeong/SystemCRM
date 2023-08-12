package com.jafa.repository.product;

import java.util.List;

import com.jafa.domain.product.PdCategoryVO;

public interface PdCategoryRepository {
	List<PdCategoryVO> readCategory(Long parentCategoryId); // 카테고리 조회
}
