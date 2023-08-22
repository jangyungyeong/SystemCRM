package com.jafa.domain.sell;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import com.jafa.domain.product.ProductVO;

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
@NoArgsConstructor
@AllArgsConstructor
@Alias("sell")
public class SellVO {
	private Long sno; // 판매번호
	private Long cno; // 고객번호
	private Long productId; // 제품번호
	private int amount; // 제품수량
	private String staffId; // 직원아이디
	
	private LocalDateTime regDate; // 판매등록일
	private LocalDateTime updateDate; // 판매수정일
}
