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
	@Autowired
	private ProductUpdater productUpdater;
	
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	public Product getProductByCode(int code) {
		Product product = productRepository.findByCode(code);
		return product;
	}

	public void deleteProductByCode(int code) {
		productRepository.deleteByCode(code);
	}

	public void addProduct(Product newProduct) {
		productRepository.insert(newProduct);
		
	}

	public void editProduct(int code, Product productWithNewParameters) {

		Product product = productRepository.findByCode(code);
		
		productUpdater.update(product, productWithNewParameters);
		
		productRepository.save(product);
	}
	
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public void setProductUpdater(ProductUpdater productUpdater) {
		this.productUpdater = productUpdater;
	}

}
