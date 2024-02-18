package com.example.ShoppingCartApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ShoppingCartApp.entities.CartItem;
import com.example.ShoppingCartApp.entities.User;
import com.example.ShoppingCartApp.services.ShoppingCartService;
import com.example.ShoppingCartApp.services.UserService;

@RestController
@RequestMapping("/shopping")
public class ShoppingCartController {

	private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/addToCart/{username}/{productId}/{quantity}")
    public ResponseEntity<Void> addToCart(@PathVariable String username, @PathVariable Long productId, @PathVariable int quantity) {
        try {
            shoppingCartService.addToCart(username, productId, quantity);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping("/removeFromCart/{username}/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable String username, @PathVariable Long cartItemId) {
    	try {
            shoppingCartService.removeFromCart(username, cartItemId);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @GetMapping("/getCart/{username}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String username) {
    	try {
    		User user = userService.getUserByUsername(username);
            if (user != null) {
                List<CartItem> cartItems = shoppingCartService.getCartItems(username);
                return ResponseEntity.ok(cartItems);
            }
            return ResponseEntity.notFound().build();
		} catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @GetMapping("/getTotal/{username}")
    public ResponseEntity<Double> getTotal(@PathVariable String username) {
    	User user = userService.getUserByUsername(username);
        if (user != null) {
            double total = shoppingCartService.getTotal(username);
            return ResponseEntity.ok(total);
        }
        return ResponseEntity.notFound().build();
    }
}
