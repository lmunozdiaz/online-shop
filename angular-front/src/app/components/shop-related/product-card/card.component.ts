import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../../model/product";
import {CartService} from "../../../services/cart.service";
import {CartItem} from "../../../model/cart-item";
import {User} from "../../../model/user";
import {UserService} from "../../../services/user.service";

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
  user: User;

  constructor(private cartService: CartService,
              private userService: UserService) { }

  ngOnInit(): void {
  }

  addToCart(product: Product) {
    // get the active session user
    this.userService.getActiveUser().subscribe(
      data => {
        this.user = data;

        // build the cart item
        let cartItem: CartItem = new CartItem(product, this.user);

        // save to backend
        this.cartService.addItem(cartItem).subscribe(
          data => { console.log(data) }
        );
      }
    );
  }
}
