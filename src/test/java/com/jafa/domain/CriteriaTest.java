package com.jafa.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class CriteriaTest {

	@Test
//	@Ignore
	public void test() {
		Criteria criteria = new Criteria();
		log.info(criteria.getType());
		String type = criteria.getType();
		log.info(type);
		
		criteria.setType("C");;
		log.info(criteria.getType());
	}

}
