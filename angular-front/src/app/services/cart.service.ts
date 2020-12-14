import { Injectable } from '@angular/core';
import {CartItem} from "../model/cart-item";
import {Observable, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  // the api url for shopping cart
  readonly baseUrl: string = 'http://localhost:8080/api/shopping-cart';

  constructor(private http: HttpClient) { }

  getCartItemsList(): Observable<any> {
    // cart endpoint url
    const cartUrl: string = `${this.baseUrl}`

    return this.http.get(cartUrl);
  }

  addItem(cartItem: CartItem): Observable<any> {
    // add item endpoint url
    const addUrl: string = `${this.baseUrl}/add-to-cart`

    return this.http.post(addUrl, cartItem);
  }

  removeItem(id: number): Observable<any> {
    // delete endpoint url
    const deleteUrl: string = `${this.baseUrl}/remove-from-cart${id}`

    return this.http.delete(deleteUrl);
  }

}
