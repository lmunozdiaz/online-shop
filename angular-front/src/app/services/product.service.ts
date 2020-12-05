import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // the backend url for products, hardcoded
  readonly baseUrl: string = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {
  }

  /**
   * The getAll() retrieves all products
   * by given category type from the backend endpoint
   * @param categoryType
   */
  getAll(categoryType: number): Observable<Product[]> {

    // the modified url for the backend endpoint
    const searchUrl = `${this.baseUrl}/search/category/${categoryType}`;

    return this.http.get<Product[]>(searchUrl);

  }

  /**
   * The getOne() retrieves a product by the given
   * id from the backend endpoint
   * @param id
   */
  getOne(id: string):Observable<Product> {

    // the modified url for the backend endpoint
    const searchUrl = `${this.baseUrl}/product/${id}`;

    return this.http.get<Product>(searchUrl);

  }

  /**
   * The getAllContainingSearchString() retrieves all products
   * that contain the given search string from the backend endpoint
   * @param searchStr
   */
  getAllContainingSearchString(searchStr: string): Observable<Product[]> {

    // the modified url for the backend endpoint
    const searchUrl = `${this.baseUrl}/search/product/${searchStr}`;

    return this.http.get<Product[]>(searchUrl);

  }
}
