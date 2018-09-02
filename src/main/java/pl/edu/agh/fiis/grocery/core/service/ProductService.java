package pl.edu.agh.fiis.grocery.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product getProductByCode(int code) {
		Product product = productRepository.findByCode(code);
		return product;
	}

	public void deleteProductByCode(int code) {
		productRepository.deleteByCode(code);
	}

}
