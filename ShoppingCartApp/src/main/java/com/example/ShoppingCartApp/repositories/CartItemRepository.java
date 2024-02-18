package com.example.ShoppingCartApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCartApp.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
