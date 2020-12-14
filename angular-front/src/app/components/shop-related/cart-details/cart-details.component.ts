import {Component, OnInit} from '@angular/core';
import {CartItem} from "../../../model/cart-item";
import {CartService} from "../../../services/cart.service";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  // to hold the cart item list
  cartItems: CartItem[];

  // the columns to display
  displayedColumns: string[] = ["imageUrl", "name", "price", "subtotal", "action"];

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.fetchCartItems();
  }

  fetchCartItems() {
    this.cartService.getCartItemsList().subscribe(
      cartItems => {
        // store the cart item list response
        this.cartItems = cartItems
      }
    );
  }
}
