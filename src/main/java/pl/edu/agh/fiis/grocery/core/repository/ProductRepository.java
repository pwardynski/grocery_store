package pl.edu.agh.fiis.grocery.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.edu.agh.fiis.grocery.core.data.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	Product findByCode(int code);
	void deleteByCode(int code);

}
