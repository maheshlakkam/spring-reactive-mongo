package com.app.reactive.resource;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.reactive.model.Product;
import com.app.reactive.model.ProductEvent;
import com.app.reactive.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/rest/product")
public class ProductResource {

	ProductRepository productRepository;
	
	public ProductResource(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	@GetMapping("/all")
	public Flux<Product> getAllProducts(){
		System.out.println("Received request");
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Product> getProductById(@PathVariable final String id){
		return productRepository.findById(id);
	}
	
	@GetMapping(value="/{id}/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Object> getEvents(@PathVariable final String id){
		/*return productRepository.findById(id)
				.flatMapMany(product -> {
					Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
					
					Flux<ProductEvent> productFlux = 
							Flux.fromStream(
									Stream.generate(() -> new ProductEvent(product, 
											new Date()))
									);
					
					return Flux.zip(interval, productFlux)
							.map(Tuple2::getT2);
					});
		}*/
		//Flux<Product>fp = productRepository.findAll();
		//fp.flat
		return productRepository.findAll()
				.flatMap(product -> {
					Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
					
					Flux<ProductEvent> productFlux = 
							Flux.fromStream(
									Stream.generate(() -> new ProductEvent(product, 
											new Date()))
									);
					
					return Flux.zip(interval, productFlux)
							.map(Tuple2::getT2);
					});
		}
}
