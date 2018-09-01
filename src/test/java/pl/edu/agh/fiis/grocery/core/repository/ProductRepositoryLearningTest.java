package pl.edu.agh.fiis.grocery.core.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.empty;
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
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void findAllProducts() {
		
		List<Product> products = productRepository.findAll();
		
		assertThat(products, is(not(empty())));
	}
}
