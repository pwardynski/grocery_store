package pl.edu.agh.fiis.grocery.core.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.agh.fiis.grocery.core.data.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-core-config.xml")
public class ProductRepositoryLearningTest {
	
	private static final int CODE_OF_DELETED_PRODUCT = 10;
	private static final int CODE_OF_ADDED_PRODUCT = 11;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void findAllProducts() {
		
		List<Product> products = productRepository.findAll();
		
		assertThat(products, is(not(empty())));
	}
	
	@Test
	public void findProductByCode() {
		
		Product product = productRepository.findByCode(1);
		
		assertThat(product, notNullValue());
		assertThat(product.getName(), is("onion"));
		
	}
	
	@Test
	public void deleteProductByCode() {
		
		
		Product product = productRepository.findByCode(CODE_OF_DELETED_PRODUCT);
		
		List<Product> products = productRepository.findAll();
		int amountOfProducts = products.size();
		
		productRepository.deleteByCode(CODE_OF_DELETED_PRODUCT);
		
		List<Product> productsAfterDelete = productRepository.findAll();
		int amountOfProductsAfterDelete = productsAfterDelete.size();
		Product productAfterDeletion = productRepository.findByCode(CODE_OF_DELETED_PRODUCT);
		
		assertThat(amountOfProductsAfterDelete, is(amountOfProducts - 1));
		assertThat(productAfterDeletion, nullValue());
		
		productRepository.insert(product);
		
	}
	
	@Test
	public void addProduct() {
	
		Product newProduct = new Product();
		newProduct.setCode(CODE_OF_ADDED_PRODUCT); 
		newProduct.setName("radish"); 
		newProduct.setCategory("vegetable"); 
		newProduct.setDescription("white carrot");
		
		List<Product> products = productRepository.findAll();
		int amountOfProducts = products.size();
		
		productRepository.insert(newProduct);
		
		List<Product> productsAfterAddition = productRepository.findAll();
		int amountOfProductsAfterAddition = productsAfterAddition.size();
		
		Product newlyAddedProduct  = productRepository.findByCode(CODE_OF_ADDED_PRODUCT);
		
		assertThat(amountOfProductsAfterAddition, is(amountOfProducts + 1));
		assertThat(newlyAddedProduct.getName(),is("radish"));
		
		productRepository.deleteByCode(CODE_OF_ADDED_PRODUCT);
		
	}
}
