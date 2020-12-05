import {Product} from "./product";

/**
 * The CartItem class models the essential
 * information of a Product for use in the cart service
 */
export class CartItem {
  id: string;
  imageUrl: string;
  name: string;
  price: number;

  quantity: number;

  constructor(product: Product) {
    this.id = product.id;
    this.imageUrl = product.imageUrl;
    this.name = product.name;
    this.price = product.price;

    this.quantity = 1;
  }
}
