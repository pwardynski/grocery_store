package pl.edu.agh.fiis.grocery.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(path="/products", method=RequestMethod.GET)
	public List<Product> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return products;
	}

	@RequestMapping(path="/products/{code}", method=RequestMethod.GET)
	public Product getProductByCode(@PathVariable("code") int code) {
		Product product = productService.getProductByCode(code);
		return product;
	}
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
