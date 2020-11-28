import { __decorate } from "tslib";
import { Component } from '@angular/core';
let ProductListComponent = class ProductListComponent {
    constructor(productService) {
        this.productService = productService;
    }
    ngOnInit() {
        this.fetchProducts();
    }
    fetchProducts() {
        this.productService.getAll(this.currentCategoryId).subscribe(data => {
            this.products = data;
            // log the data for debugging
            console.log(data);
        });
    }
};
ProductListComponent = __decorate([
    Component({
        selector: 'app-product-list',
        templateUrl: './product-list.component.html',
        styleUrls: ['./product-list.component.css']
    })
], ProductListComponent);
export { ProductListComponent };
//# sourceMappingURL=product-list.component.js.map
