import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  // the backend url for products, hardcoded
  readonly baseUrl: string = 'http://localhost:8080/api/categories';

  constructor(private http: HttpClient) {
  }

  /**
   * The getAll() retrieves all category types
   * from the backend endpoint
   */
  getAll(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }
}
