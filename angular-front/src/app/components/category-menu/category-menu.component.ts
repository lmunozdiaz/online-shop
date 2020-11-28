import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {Category} from "../../model/category";

@Component({
  selector: 'app-category-menu',
  templateUrl: './category-menu.component.html',
  styleUrls: ['./category-menu.component.css']
})
export class CategoryMenuComponent implements OnInit {

  // to hold the fetched categories
  categories: Category[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.fetchCategories();
  }

  /**
   * The fetchCategories() retrieves all categories
   * to dynamically display the category list.
   */
  fetchCategories() {
    this.categoryService.getAll().subscribe(
      data => {
        this.categories = data;
        //log the data for debugging
        console.log(data);
      }
    );
  }
}
