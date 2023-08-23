package com.jafa.service.sell;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jafa.domain.sell.SellDTO;
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
	public List<SellDTO> SellList() {
		return sellRepository.sellList();
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


}
