package pl.edu.agh.fiis.grocery.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-mvc-config.xml",
		"file:src/main/webapp/WEB-INF/spring-core-config.xml"})
public class ProductIntegrationTest {
	
	private static final int CODE_OF_DELETED_PRODUCT = 10;
	private static final int CODE_OF_ADDED_PRODUCT = 11;
	private static final int CODE_OF_UPGRADED_PRODUCT = 9;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void getAllProducts() throws Exception {
		mockMvc.perform(get("/products"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("onion")))
		.andExpect(jsonPath("$[1].name", is("potato")));
	}
	
	@Test
	public void getProductByCode() throws Exception {
		mockMvc.perform(get("/products/2"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("potato")));
	}
	
	@Test
	public void deleteProductByCode() throws Exception {
		Product product = productRepository.findByCode(CODE_OF_DELETED_PRODUCT);
		
		mockMvc.perform(delete("/products/"+CODE_OF_DELETED_PRODUCT))
		.andExpect(status().isOk());
		
		Product productAfterDeletion = productRepository.findByCode(CODE_OF_DELETED_PRODUCT);
		assertThat(productAfterDeletion, nullValue());
		
		productRepository.insert(product);
	}
	
	@Test
	public void addProduct() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product product = new Product();
		product.setCode(CODE_OF_ADDED_PRODUCT); 
		product.setName("radish"); 
		product.setCategory("vegetable"); 
		product.setDescription("white carrot");
		
		String productJson = mapper.writeValueAsString(product);
		
		mockMvc.perform(
				post("/products/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productJson)
				)
				.andExpect(status().isOk());
		
		Product newlyAddedProduct = productRepository.findByCode(CODE_OF_ADDED_PRODUCT);
		assertThat(newlyAddedProduct, notNullValue());
		
		productRepository.deleteByCode(CODE_OF_ADDED_PRODUCT);
	}
	
	@Test
	public void editProduct() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product product = new Product();
		product.setCode(CODE_OF_ADDED_PRODUCT); 
		product.setName("radish"); 
		product.setCategory("fruit"); 
		product.setDescription("white carrot");
		
		String productJson = mapper.writeValueAsString(product);
		
		Product productBeforeUpdating = productRepository.findByCode(CODE_OF_UPGRADED_PRODUCT);
		
		mockMvc.perform(
				put("/products/"+ CODE_OF_UPGRADED_PRODUCT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(productJson)
				)
				.andExpect(status().isOk());
		
		Product newlyUpdatedProduct = productRepository.findByCode(CODE_OF_UPGRADED_PRODUCT);
		assertThat(newlyUpdatedProduct.getCategory(), is("fruit"));
		
		productRepository.save(productBeforeUpdating);
	}
}
