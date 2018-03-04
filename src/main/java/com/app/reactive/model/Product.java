package com.app.reactive.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	private String id;
	private String description;
	private Long quantity;
	
	public Product(String id, String description, Long quantity) {
		this.id = id;
		this.description = description;
		this.quantity = quantity;
	}
	public Product() {}
	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", quantity=" + quantity + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
