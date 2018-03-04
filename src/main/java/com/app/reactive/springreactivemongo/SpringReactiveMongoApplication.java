package com.app.reactive.springreactivemongo;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.app.reactive.model.Product;
import com.app.reactive.repository.ProductRepository;

@EnableReactiveMongoRepositories(basePackages="com.app.reactive.repository")
@ComponentScan(basePackages="com.app.reactive")
@SpringBootApplication
public class SpringReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveMongoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner products(ProductRepository repository) {
		return args -> {
				repository
					.deleteAll()
					.subscribe(null,null,() -> {
						Stream.of(new Product(UUID.randomUUID().toString(),"Mobile",100L),
								new Product(UUID.randomUUID().toString(),"Computer",200L),
								new Product(UUID.randomUUID().toString(),"CAR",300L),
								new Product(UUID.randomUUID().toString(),"BOOK",400L))
						.forEach(product ->{
							repository
								.save(product)
								.subscribe(System.out::println);
						});
					});
		};
	}
}
