package com.example.ShoppingCartApp.services;

import java.util.List;

import com.example.ShoppingCartApp.entities.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
}
