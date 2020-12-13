import { Component, OnInit } from '@angular/core';
import {CartService} from "../../../services/cart.service";

@Component({
  selector: 'app-cart-status',
  templateUrl: './cart-status.component.html',
  styleUrls: ['./cart-status.component.css']
})
export class CartStatusComponent implements OnInit {

  // to hold the cart's total quantity
  // totalQuantity: number = 0;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {

  }

  // /**
  //  * The updateCartStatus() will retrieve
  //  * the totalQuantity from the cart service class
  //  * and publish it in the cart-status html page.
  //  * @private
  //  */
  // private updateCartStatus() {
  //
  //   // subscribe to the cart service's Subject variable 'totalQuantity'
  //   // to display the quantity badge on the cart button
  //   this.cartService.totalQuantity.subscribe(
  //     data => {
  //       this.totalQuantity = data
  //     }
  //   );
  //
  // }
}
