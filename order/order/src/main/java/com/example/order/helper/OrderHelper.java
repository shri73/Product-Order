package com.example.order.helper;

import java.math.BigDecimal;
import java.util.List;

import com.example.order.model.Product;

public class OrderHelper {

	public static BigDecimal countTotalPrice(List<Product> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total = total.add(cart.get(i).getPrice().multiply(BigDecimal.valueOf(cart.get(i).getQuantity())));
        }
        return total;
    }
}
