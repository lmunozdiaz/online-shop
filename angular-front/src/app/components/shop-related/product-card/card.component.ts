import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../../model/product";
import {CartService} from "../../../services/cart.service";
import {CartItem} from "../../../model/cart-item";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  // the following annotated variables
  // are assigned data via interpolation when
  // using this component's selector
  @Input() id: string;
  @Input() imageUrl: string;
  @Input() name: string;

  // to hold the product that will be
  // sent to the cart, if requested
  @Input() product: Product;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
  }

  // @TODO: add to server side cart
  addToCart(product: Product) {

  }
}
