package com.app.reactive.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductEvent {
	
	private Product product;
	private Date date;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ProductEvent(Product product, Date date) {
		super();
		this.product = product;
		this.date = date;
	}
}
