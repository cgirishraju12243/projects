package com.example.ShoppingCartApp.services;

import java.util.List;

import com.example.ShoppingCartApp.entities.CartItem;

public interface ShoppingCartService {
	
	void addToCart(String username, Long productId, int quantity);

    void removeFromCart(String username, Long productId);

    List<CartItem> getCartItems(String username);

    double getTotal(String username);
}
