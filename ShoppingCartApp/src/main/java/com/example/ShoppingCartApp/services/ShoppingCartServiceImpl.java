package com.example.ShoppingCartApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingCartApp.entities.CartItem;
import com.example.ShoppingCartApp.entities.User;
import com.example.ShoppingCartApp.repositories.CartItemRepository;
import com.example.ShoppingCartApp.repositories.UserRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	private final ProductService productService;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public ShoppingCartServiceImpl(ProductService productService, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addToCart(String username, Long productId, int quantity) {
    	User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            // Check if the product is already in the user's cart
            Optional<CartItem> existingCartItem = existingUser.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                // Product already in the cart, update the quantity
                CartItem cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                // Product not in the cart, create a new cart item
                CartItem cartItem = new CartItem(existingUser, productService.getProductById(productId), quantity);
                existingUser.getCartItems().add(cartItem);
            }

            userRepository.save(existingUser);
        }
    }

    @Override
    public void removeFromCart(String username, Long cartItemId) {
    	Optional<User> existingUserOptional = Optional.ofNullable(userRepository.findByUsername(username));

        existingUserOptional.ifPresent(existingUser -> {
            List<CartItem> cartItems = existingUser.getCartItems();
            cartItems.removeIf(item -> item.getId().equals(cartItemId));
            existingUser.setCartItems(cartItems);

            userRepository.save(existingUser);

            cartItemRepository.deleteById(cartItemId);
        });
    }

    @Override
    public List<CartItem> getCartItems(String username) {
    	Optional<User> existingUserOptional = Optional.ofNullable(userRepository.findByUsername(username));
        return existingUserOptional
                .map(User::getCartItems)
                .orElse(List.of());
    }

    @Override
    public double getTotal(String username) {
    	Optional<User> existingUserOptional = Optional.ofNullable(userRepository.findByUsername(username));
        return existingUserOptional
                .map(u -> u.getCartItems().stream()
                        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                        .sum())
                .orElse(0.0);
    }

}
