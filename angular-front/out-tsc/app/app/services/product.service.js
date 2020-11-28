import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let ProductService = class ProductService {
    constructor(http) {
        this.http = http;
        this.baseUrl = 'http://localhost:8080/api/products';
    }
    getAll() {
        return this.http.get(this.baseUrl);
    }
};
ProductService = __decorate([
    Injectable({
        providedIn: 'root'
    })
], ProductService);
export { ProductService };
//# sourceMappingURL=product.service.js.map