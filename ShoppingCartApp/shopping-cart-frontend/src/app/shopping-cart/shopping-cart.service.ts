import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from '../models/cart-item.model';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  private apiUrl = 'http://localhost:8080/shopping';

  constructor(private http: HttpClient) {}

  addToCart(username: string, productId: number, quantity: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/addToCart/${username}/${productId}/${quantity}`, {});
  }

  removeFromCart(username: string, cartItemId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/removeFromCart/${username}/${cartItemId}`);
  }

  getCartItems(username: string): Observable<CartItem[]> {
    return this.http.get<CartItem[]>(`${this.apiUrl}/getCart/${username}`);
  }

  getTotal(username: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/getTotal/${username}`);
  }
}
