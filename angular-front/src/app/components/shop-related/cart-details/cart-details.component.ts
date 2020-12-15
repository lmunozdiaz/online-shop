import {Component, OnInit} from '@angular/core';
import {CartItem} from "../../../model/cart-item";
import {CartService} from "../../../services/cart.service";
import { Order } from 'src/app/model/order';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  // to hold the cart item list
  cartItems: CartItem[] = [];
  order:Order;
  // the columns to display
  displayedColumns: string[] = ["imageUrl", "name", "price", "subtotal", "action"];

  constructor(private cartService: CartService,private router: Router,private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.fetchCartItems();
  }
  placeOrder() {
    this.cartService.placeOrder().subscribe(
      order => {
        // store the cart item list response
        this.order = order;
        alert('Order Placed Successfully' + this.order.id);
        let element = document.getElementById('cartCount') as HTMLElement;
        if (element != null) {
          element.children[0].innerHTML = 0+'';
        }
        this.router.navigateByUrl(`/orders/orderDetails/` + this.order.id);
      }, error => {
        alert('Error Creating Order' + error);
      }
    );
  }
  fetchCartItems() {
    this.cartService.getCartItemsList().subscribe(
      cartItems => {
        // store the cart item list response
        this.cartItems = cartItems
      }
    );
  }
  removeCartItem(cartItemToRemove: CartItem) {
    this.cartService.removeItem(cartItemToRemove.product.id).subscribe(
      data => {
        console.log('The remove button returned: ');
        console.log(data);
        const snack = this._snackBar.open(cartItemToRemove.product.name+' Removed from Cart', '', {
                  duration: 5000,
                  verticalPosition: 'top'
        });
        window.location.reload();
      },error => {
        window.location.reload();
      }
    );
  }
}
