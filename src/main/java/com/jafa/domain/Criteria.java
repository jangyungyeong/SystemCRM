package com.jafa.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Criteria {
		private int pageNum;
		private int amount;
		
		private String type; 
		private String keyword;
		
		public Criteria() {
			this(1,10);
		}

		public Criteria(int pageNum, int amount) {
			this.pageNum = pageNum;
			this.amount = amount;
		}
		
		public int getMaxRow() {
			return pageNum*amount;
		}
		
		public int getMinRow() {
			return (pageNum-1)*amount;
		}
		
		public String getType() {
			return type == null ? "" : type;
		}
		
		public String getListLink() {
			UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
					.queryParam("pageNum",this.pageNum)
					.queryParam("amount",this.amount)
					.queryParam("type",this.type)
					.queryParam("keyword",this.keyword);
			return builder.toUriString();
		}

}
