package com.jafa.service.sell;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jafa.domain.Criteria;
import com.jafa.domain.sell.PdCategoryDTO;
import com.jafa.domain.sell.ProductVO;
import com.jafa.domain.sell.SellDTO;
import com.jafa.domain.sell.SellProduct;
import com.jafa.domain.sell.SellVO;
import com.jafa.repository.sell.SellRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class SellServiceImpl implements SellService{

	private final SellRepository sellRepository;
	
	@Override
	public List<SellDTO> SellList(Criteria criteria) {
		return sellRepository.sellList(criteria);
	}

	@Override
	public void register(SellVO sell) {
		sellRepository.insertSelectKey(sell);
	}

	@Override
	public SellDTO get(Long cno) {
		return sellRepository.read(cno);
	}

	@Override
	public boolean modify(SellVO sell) {
		return sellRepository.update(sell) == 1;
	}

	@Override
	public boolean remove(Long cno) {
		return sellRepository.delete(cno) == 1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return sellRepository.getTotalCount(criteria);
	}

	@Override
	public List<SellProduct> ProductList(Long cno) {
		return sellRepository.productList(cno);
	}

	@Override
	public List<PdCategoryDTO> getPdcategoryList(Integer parentcategoryId) {
		return sellRepository.getProductCategory(parentcategoryId);
	}

	@Override
	public List<ProductVO> getPdList(Integer categoryId) {
		return sellRepository.getProductList(categoryId);
	}

	@Override
	public ProductVO productInfo(Integer productId) {
		return sellRepository.productInfo(productId);
	}


}
