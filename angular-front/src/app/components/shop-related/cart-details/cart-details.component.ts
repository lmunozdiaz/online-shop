import { Component, OnInit } from '@angular/core';
import {CartItem} from "../../../model/cart-item";
import {CartService} from "../../../services/cart.service";
import {Subject} from "rxjs";
import {Product} from "../../../model/product";
import {ProductService} from "../../../services/product.service";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[];
  products: Product[] = [];

  constructor(private cartService: CartService,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.fetchCartItems();
  }

  fetchCartItems() {
    this.cartService.getCartItemsList().subscribe(
      cartItems => {
        this.cartItems = cartItems
        console.log("The cartItems list: ");
        console.log(cartItems);

        for (let cartItem of this.cartItems) {
          // find the product with the cartItem's product_id
          this.productService.getProduct(cartItem.product.id).subscribe(
            product => { this.products.push(product);
            console.log("The product fetched: ");
            console.log(product);
            }
          );
        }
      }
    );
  }
}
