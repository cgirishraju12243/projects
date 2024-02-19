// shopping-cart.component.ts
import { Component, OnInit } from '@angular/core';
import { CartItem } from '../models/cart-item.model';
import { ShoppingCartService } from './shopping-cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
})
export class ShoppingCartComponent implements OnInit {
  cartItems: CartItem[] = [];

  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {
    this.loadCartItems();
  }

  loadCartItems(): void {
    const username = 'exampleUser';

    this.shoppingCartService.getCartItems(username).subscribe((data) => (this.cartItems = data));
  }
}
