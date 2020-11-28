import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  readonly baseUrl: string = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {
  }

  /**
   * The getAll() retrieves all products
   * by given category ID from the spring boot endpoint.
   * @param categoryId
   */
  getAll(categoryId: number): Observable<Product[]> {

    // the spring boot endpoint url
    const searchUrl = `${this.baseUrl}/search/category/${categoryId}`

    return this.http.get<Product[]>(searchUrl);
  }
}
