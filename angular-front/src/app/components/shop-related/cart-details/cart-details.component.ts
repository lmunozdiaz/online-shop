import { Component, OnInit } from '@angular/core';
import {CartItem} from "../../../model/cart-item";
import {CartService} from "../../../services/cart.service";
import {merge, Observable, Subject} from "rxjs";
import {Product} from "../../../model/product";
import {ProductService} from "../../../services/product.service";
import {mergeMap, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[];

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.fetchCartItems();
  }

  fetchCartItems() {
    this.cartService.getCartItemsList().subscribe(
      cartItems => {
        this.cartItems = cartItems
      }
    );
  }
}
