package pl.edu.agh.fiis.grocery.web;

import java.util.List;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.service.ProductService;

public class ProductController {
	
	private ProductService productService;

	public List<Product> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return products;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
