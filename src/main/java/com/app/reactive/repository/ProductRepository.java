package com.app.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.app.reactive.model.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
