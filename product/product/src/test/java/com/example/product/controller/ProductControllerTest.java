package com.example.product.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.product.ProductApplication;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ContextConfiguration(classes=ProductApplication.class)
@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	 	private static final String PRODUCT_NAME= "test";
	    private static final Long PRODUCT_ID = 3L;
	    

	    private List<Product> products;
	    private Product product;
	    private ObjectMapper objectMapper = new ObjectMapper();

	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private ProductService productService;
	    
	    @BeforeEach
	    public void setUp(){
	        product = new Product();
	        product.setId(PRODUCT_ID);
	        product.setName(PRODUCT_NAME);
	        product.setDescription("test test");
	        product.setQuantity(50);
	        product.setPrice(new BigDecimal(80));
	        products = new ArrayList<Product>();
	        products.add(product);
	    }
	    
	    @Test
	    public void getProductByIdTest() throws Exception {

	        when(productService.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));

	        mockMvc.perform(get("/products/{id}", PRODUCT_ID))
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(PRODUCT_ID))
	                .andExpect(jsonPath("$.name").value(PRODUCT_NAME))
	                
	                .andDo(print());

	        verify(productService, times(1)).findById(PRODUCT_ID);
	        verifyNoMoreInteractions(productService);
	    }
	    
	    @Test
	    public void addProductTest() throws Exception {
	        String productJson = objectMapper.writeValueAsString(product);
	        when(productService.save(new Product())).thenReturn(product);

	        mockMvc.perform(post("/products/add").content(productJson).contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated());

	        verify(productService, times(1)).save(product);
	        verifyNoMoreInteractions(productService);
	    }
	    
	    @Test
	    public void getAllProductByNameTest() throws Exception {

	        given(productService.findAllProductsByName(PRODUCT_NAME)).willReturn(products);

	        mockMvc.perform(get("/products").param("name", PRODUCT_NAME))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].id").value(PRODUCT_ID))
	                .andDo(print());


	        verify(productService, times(1)).findAllProductsByName(Mockito.anyString());
	        verifyNoMoreInteractions(productService);
	    }

}
