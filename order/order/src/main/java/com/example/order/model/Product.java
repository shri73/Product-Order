package com.example.order.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import javax.persistence.Id;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long productId;

    @Transient
    private Long id;
    
    @Column
    @NotNull
    private Integer quantity;

    @Column
    @NotNull
    private String productName;
    
    @Column 
    @NotNull
    private BigDecimal price;
    
    @ManyToMany (mappedBy = "items")
    @JsonIgnore
    private List<OrderDetails> orderDetails;

}
