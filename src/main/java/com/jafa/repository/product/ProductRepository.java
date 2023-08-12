package com.jafa.repository.product;

import java.util.List;

import com.jafa.domain.product.ProductDTO;
import com.jafa.domain.product.ProductVO;

public interface ProductRepository {
	List<ProductVO> list(Long categoryId); // 하위카테고리 조회
	ProductDTO productInfo(Long productId); // 상품 조회
}
