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

  addCategory(categoryToAdd: Category) {

    // the modified url for the backend endpoint
    const searchUrl = `${this.baseUrl}/add`;

    this.http.put(searchUrl, categoryToAdd);

  }

  editCategory(categoryToEdit: Category) {

    // the modified url for the backend endpoint
    const searchUrl = `${this.baseUrl}/edit`;

    this.http.put(searchUrl, categoryToEdit);

  }

  deleteCategory(categoryId: number) {

    // the modified url for the backend endpoint
    const searchUrl = `${this.baseUrl}/delete/${categoryId}`;

    return this.http.delete(searchUrl);

  }

}
