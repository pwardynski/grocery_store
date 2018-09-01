package pl.edu.agh.fiis.grocery.core.service;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	ProductRepository productRepositoryMock;
	
	@Test
	public void getAllProducts() {
		ProductService productService = new ProductService();
		productService.setProductRepository(productRepositoryMock);
		List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
		when(productRepositoryMock.findAll()).thenReturn(expectedProducts);
		
		List<Product> actualProducts = productService.getAllProducts();
		
		verify(productRepositoryMock).findAll();
		assertThat(actualProducts, is(expectedProducts));
	}
}
