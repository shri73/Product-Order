package com.example.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTests {
	
	private Product product;
	private List<Product> products;
	
	@Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;
    
    @BeforeEach
    public void setUp(){
        product = new Product();
        product.setId(3L);
        product.setName("test");
        product.setDescription("test");
        product.setQuantity(50);
        product.setPrice(new BigDecimal(80));
        products = new ArrayList<Product>();
        products.add(product);
    }
    
    @Test
    public void findByIdTest(){
        // Data preparation
        Mockito.when(productRepository.findById(3L)).thenReturn(Optional.ofNullable(product));

        // Method call
        Optional<Product> found = productService.findById(3L);
        assertTrue(found.isPresent());

        Mockito.verify(productRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(productRepository);
    }
    
    @Test
    public void updateQuantityTest(){
        // Data preparation
        Mockito.when(productRepository.findById(3L)).thenReturn(Optional.ofNullable(product));

        // Method call
        productService.updateQuantityOfProduct(3L, 20);
        Optional<Product> found = productService.findById(3L);
        assertEquals(found.get().getQuantity(), 30);
        
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.anyObject());
        
    }
    
    @Test
    public void findAllByProductNameTest(){
          //Data preparation
    	  Mockito.when(productRepository.findAll()).thenReturn(products);

    	  //Method call
          List<Product> foundProducts = productService.findAllProductsByName("test");

          //Verification
          assertEquals(foundProducts.get(0).getName(), "test");
          Mockito.verify(productRepository, Mockito.times(1)).findAll();
          Mockito.verifyNoMoreInteractions(productRepository);
    }
    
    @Test
    public void findAllByQuantityTest(){
        	//Data preparation
    	  Mockito.when(productRepository.findAll()).thenReturn(products);

    	  //Method call
          List<Product> foundProducts = productService.findAllProductsByQuantity(50);

          // Verification
          assertEquals(foundProducts.get(0).getQuantity(), 50);
          Mockito.verify(productRepository, Mockito.times(1)).findAll();
          Mockito.verifyNoMoreInteractions(productRepository);
    }


    

}
