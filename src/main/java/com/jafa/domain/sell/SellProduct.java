package com.jafa.domain.sell;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("sellprod")
public class SellProduct {
	private Long cno;
	private String productName;
	private String productNumber;
	private int amount;
	private int price;
	private int total;
}
