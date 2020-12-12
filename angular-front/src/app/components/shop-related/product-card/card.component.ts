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

  /**
   * TODO: write a description
   * @param product
   */
  addToCart(product: Product) {

    // for debugging
    console.log(`product_name: ${product.name} -- product_price: ${product.price}`);

    // the variable 'itemToAdd' is the item to be
    // to be added to the cart; the CartItem class instance
    // is a simplified Product object meant simply
    // for transferring data. DTO.
    const itemToAdd = new CartItem(product)

    this.cartService.addToCart(itemToAdd);
  }
}
