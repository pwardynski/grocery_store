package pl.edu.agh.fiis.grocery.core.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
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
	
	@Mock
	ProductUpdater productUpdaterMock;
	
	ProductService productService; 
	
	
	@Before
	public void setUp() {
		productService = new ProductService();
		productService.setProductRepository(productRepositoryMock);
		productService.setProductUpdater(productUpdaterMock);
	}
	
	@Test
	public void getAllProducts() {
		List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
		when(productRepositoryMock.findAll()).thenReturn(expectedProducts);
		
		List<Product> actualProducts = productService.getAllProducts();
		
		verify(productRepositoryMock).findAll();
		assertThat(actualProducts, is(expectedProducts));
	}
	
	@Test
	public void getProductByCode() {
		Product expectedProduct = new Product();
		when(productRepositoryMock.findByCode(1)).thenReturn(expectedProduct);
		
		Product actualProduct = productService.getProductByCode(1);
		
		verify(productRepositoryMock).findByCode(1);
		assertThat(actualProduct, is(expectedProduct));
	}
	
	@Test
	public void deleteProductByCode() {
		
		productService.deleteProductByCode(10);
		
		verify(productRepositoryMock).deleteByCode(10);
	}
	
	@Test
	public void addProduct() {
		
		Product newProduct = new Product();
		newProduct.setCode(11); 
		newProduct.setName("radish"); 
		newProduct.setCategory("vegetable"); 
		newProduct.setDescription("white carrot");
		
		productService.addProduct(newProduct);
		
		verify(productRepositoryMock).insert(newProduct);
	}
	
	@Test
	public void editProduct() {
		
		/*Product productWithNewParameters = new Product();
		productWithNewParameters.setCode(8); 
		productWithNewParameters.setName("radish"); 
		productWithNewParameters.setCategory("vegetable"); 
		productWithNewParameters.setDescription("white carrot");*/
		
		Product productToUpdate = new Product() ;

		when(productRepositoryMock.findByCode(9)).thenReturn(productToUpdate);
		
		Product productWithNewParameters = new Product();
		
		productService.editProduct(9, productWithNewParameters);
		
		verify(productUpdaterMock).update(productToUpdate, productWithNewParameters);
		verify(productRepositoryMock).save(productToUpdate);
	}
}
