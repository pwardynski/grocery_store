package pl.edu.agh.fiis.grocery.web;

import java.util.List;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.service.ProductService;

public class ProductController {

	public List<Product> getAllProducts() {
		ProductService productService = getProductService();
		List<Product> products = productService.getAllProducts();
		return products;
	}

	public ProductService getProductService() {
		return new ProductService();
	}

}
