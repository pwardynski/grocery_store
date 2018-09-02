package pl.edu.agh.fiis.grocery.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@Mock
	ProductService productServiceMock;
	
	ProductController productController; 
	
	@Before
	public void setUp() {
		productController = new ProductController();
		productController.setProductService(productServiceMock);
	}
	
	@Test
	public void getAllProducts() {

		List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
		when(productServiceMock.getAllProducts()).thenReturn(expectedProducts);
		
		List<Product> actualProducts = productController.getAllProducts();
		
		verify(productServiceMock).getAllProducts();
		assertThat(actualProducts, is(expectedProducts));
		
		
	}
	
	@Test
	public void getProductByCode() {

		Product expectedProduct = new Product();
		
		when(productServiceMock.getProductByCode(1)).thenReturn(expectedProduct);
		
		Product actualProduct = productController.getProductByCode(1);
		
		verify(productServiceMock).getProductByCode(1);
		assertThat(actualProduct, is(expectedProduct));
		
		
	}
}
