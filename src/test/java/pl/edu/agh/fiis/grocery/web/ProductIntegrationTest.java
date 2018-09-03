package pl.edu.agh.fiis.grocery.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.edu.agh.fiis.grocery.core.data.Product;
import pl.edu.agh.fiis.grocery.core.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-mvc-config.xml",
		"file:src/main/webapp/WEB-INF/spring-core-config.xml"})
public class ProductIntegrationTest {
	
	private static final int CODE_OF_DELETED_PRODUCT = 10;
	private static final int CODE_OF_ADDED_PRODUCT = 11;
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
		
		Product product = new Product();
		product.setCode(CODE_OF_ADDED_PRODUCT); product.setName("radish"); product.setCategory("vegetable"); product.setDescription("white carrot");
		
		mockMvc.perform(post("/products/"))
		.andExpect(status().isOk());
		
		//Product productAfterDeletion = productRepository.findByCode(CODE_OF_DELETED_PRODUCT);
		//assertThat(productAfterDeletion, nullValue());
		
		productRepository.deleteByCode(CODE_OF_ADDED_PRODUCT);
	}
}
