package pl.edu.agh.fiis.grocery.core.service;

import java.util.List;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
