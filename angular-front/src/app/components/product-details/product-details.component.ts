import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  // to hold the product to display
  product: Product;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.paramMap.subscribe(
      () => {
        this.handleProductDetails();
      }
    )
  }

  private handleProductDetails() {

    // get the product id from the route
    const id: string = this.route.snapshot.paramMap.get('id');

    // get the product via the product service using the id
    this.productService.getOne(id).subscribe(
      data => {
        this.product = data;
      }
    );

  }
}
