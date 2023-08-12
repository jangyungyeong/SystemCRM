package com.jafa.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.domain.Criteria;
import com.jafa.domain.customer.CustomerVO;
import com.jafa.repository.customer.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Override
	public List<CustomerVO> customerList(Criteria criteria) {
		return customerRepository.customerList(criteria);
	}

	@Override
	public void register(CustomerVO customer) {
		customerRepository.insertSelectKey(customer);
	}

	@Override
	public CustomerVO get(Long cno) {
		return customerRepository.read(cno);
	}

	@Override
	public boolean modify(CustomerVO customer) {
		return customerRepository.update(customer)==1;
	}

	@Override
	public boolean remove(Long cno) {
		return customerRepository.delete(cno)==1;
	}


	@Override
	public int totalCount(Criteria criteria) {
		return customerRepository.getTotalCount(criteria);
	}

}
