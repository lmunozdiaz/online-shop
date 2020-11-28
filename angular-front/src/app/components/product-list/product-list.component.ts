import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";
import {Product} from "../../model/product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];
  currentCategoryId: number;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.fetchProducts();
    })
  }

  private fetchProducts() {
    // check if 'id' parameter is present
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
    if (hasCategoryId) {
      // get the 'id' param string and convert to number
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
    } else {
      // category 'id' no present
      this.currentCategoryId = 1;
    }

    // retrieve products for given category id
    this.productService.getAll(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
        // log the data for debugging
        console.log(data);
      }
    );
  }
}
