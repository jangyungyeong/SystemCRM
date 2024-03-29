package com.jafa.repository.sell;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.domain.Criteria;
import com.jafa.domain.sell.PdCategoryDTO;
import com.jafa.domain.sell.ProductVO;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellProduct;
import com.jafa.domain.sell.SellVO;

public interface SellRepository {

	// 판매목록
	List<SellDTO> sellList(Criteria criteria);
	
	// 판매등록
	void insert(SellVO vo);
	
	// 등록된 행의 개수
	Integer insertSelectKey(SellVO vo);
	
	// 판매 조회
	SellDTO read(Long cno);
	
	// 수정된 행의 개수
	int update(SellVO vo);
	
	// 삭제된 행의 개수
	int delete(Long cno);
	
	// 전체 판매내역 수
	int getTotalCount(Criteria criteria);
	
	// 회원이 구매한 품목 목록
	List<SellProduct> productList(Long cno);
	
	// 물품 분류
	List<PdCategoryDTO> getProductCategory(Integer parentcategoryId);
	
	// 상품 분류
	List<ProductVO> getProductList(Integer categoryId);
	
	// 상품정보 조회
	ProductVO productInfo(Integer productId);
	
}
