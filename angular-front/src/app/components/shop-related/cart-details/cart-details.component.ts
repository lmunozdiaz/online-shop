import { Component, OnInit } from '@angular/core';
import {CartItem} from "../../../model/cart-item";
import {CartService} from "../../../services/cart.service";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  // NOTE: CartItem class is similar to the Product class
  // except it only has the essential fields,
  // like product name, price, and description

  // to hold the cart items
  // cartItems: CartItem[] = [];
  // to hold the total price
  // totalPrice: number = 0;
  // to hold the total quantity
  // totalQuantity: number = 0;

  // the columns to display in the table
  // displayedColumns: string[] = ['imageUrl', 'product-detail', 'subtotal'];

  constructor(private cartService: CartService) { }

  ngOnInit(): void {

  }

  // /**
  //  * The fetchCartDetails() will retrieve
  //  * data for displaying shopping cart items, total
  //  * quantity, and total price from the cart service.
  //  * @private
  //  */
  // private fetchCartDetails() {
  //
  //   // get the cart items from the cart service
  //   this.cartItems = this.cartService.cartItems;
  //
  //   // subscribe to the Subject variables in the cart service;
  //   // this will assign data to the totalPrice and totalQuantity
  //   // of this class whenever the Subject variables are updated in the cart service
  //   this.cartService.totalPrice.subscribe(
  //     data => {
  //       this.totalPrice = data;
  //     }
  //   );
  //   this.cartService.totalQuantity.subscribe(
  //     data => {
  //       this.totalQuantity = data;
  //     }
  //   )
  //
  //   // calculate the totals using the cart service
  //   this.cartService.computeCartTotals();
  // }

  // /**
  //  * The changeQuantity() sets the cart item's
  //  * quantity to a new value.
  //  * @param cartItem
  //  * @param newQuantity
  //  */
  // changeQuantity(cartItem: CartItem, newQuantity: number) {
  //
  //   // set the cart item's new quantity
  //   cartItem.quantity = newQuantity;
  //
  //   // recalculate the totals using the cart service
  //   this.cartService.computeCartTotals();
  //
  // }
}
