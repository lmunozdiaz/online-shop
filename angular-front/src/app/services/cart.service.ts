import { Injectable } from '@angular/core';
import {CartItem} from "../model/cart-item";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  // to hold the items (products) in the cart
  // cartItems: CartItem[] = [];

  // to hold the total price and quantity; any subscriber to a
  // Subject variable will receive any updates to its data.
  // totalPrice: Subject<number> = new Subject<number>();
  // totalQuantity: Subject<number> = new Subject<number>();

  constructor() { }

  // /**
  //  * The addToCart() will either update an
  //  * existing cart item's quantity, or add
  //  * a new cart item if it doesn't exist.
  //  * @param itemToAdd
  //  */
  // addToCart(itemToAdd: CartItem) {
  //
  //   // to hold whether 'itemToAdd' is already in the cart or not
  //   let isAlreadyInCart: boolean = false;
  //   // to hold the existing cart item
  //   let existingCartItem: CartItem = undefined;
  //
  //   // is the cart empty?
  //   if (this.cartItems.length > 0) {
  //     // no, it's not; try to find the item in the cart
  //     for (let tempCartItem of this.cartItems) {
  //       if (tempCartItem.id === itemToAdd.id) {
  //         // found the item in the cart; store it
  //         existingCartItem = tempCartItem;
  //         // get out of the loop
  //         break;
  //       }
  //     }
  //     // store the fact that the item was already in the cart
  //     isAlreadyInCart = (existingCartItem != undefined);
  //   }
  //
  //   // is the item already in the cart?
  //   if (isAlreadyInCart) {
  //     // yes, so only increment the quantity
  //     existingCartItem.quantity++;
  //   } else {
  //     // no, it wasn't; add it to the cart
  //     this.cartItems.push(itemToAdd);
  //   }
  //
  //   // compute cart totals
  //   this.computeCartTotals();
  //
  // }

  // /**
  //  * The computeCartTotals() will calculate
  //  * the totalPriceValue and totalQuantityValue
  //  * that will be assigned to totalPrice and
  //  * totalQuantity Subject variables.
  //  */
  // computeCartTotals() {
  //
  //   // to hold the values that will be assigned to
  //   // the totalPrice and totalQuantity Subject variables
  //   let totalPriceValue: number = 0;
  //   let totalQuantityValue: number =0;
  //
  //   for (let currentCartItem of this.cartItems) {
  //     // calculate total price and total quantity
  //     totalPriceValue += currentCartItem.quantity * currentCartItem.price;
  //     totalQuantityValue += currentCartItem.quantity;
  //   }
  //
  //   // send the calculated data to all of the subscribers
  //   // of the Subject variables totalPrice and totalQuantity
  //   this.totalPrice.next(totalPriceValue);
  //   this.totalQuantity.next(totalQuantityValue);
  //
  // }
}
