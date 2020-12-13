import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {CategoryService} from "../../../services/category.service";
import {Router} from "@angular/router";
import {DOCUMENT} from "@angular/common";
import {Product} from "../../../model/product";

@Component({
  selector: 'app-category-roster',
  templateUrl: './category-roster.component.html',
  styleUrls: ['./category-roster.component.css']
})
export class CategoryRosterComponent implements OnInit, AfterViewInit {

  dataSource = new MatTableDataSource();
  displayedColumns: string[] = ['name', 'categoryType', 'createTime', 'updateTime', 'action'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private categoryService: CategoryService, private router: Router,
              @Inject(DOCUMENT) private _document: Document) { }

  ngOnInit(): void {
    this.fetchAllCategories()
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  fetchAllCategories() {

    this.categoryService.getAll().subscribe(

      data => {
        this.dataSource.data = data;
      }, error => {
        console.log(error);
      }
    );

  }

  onDelete(categoryId: number) {

    this.categoryService.deleteCategory(categoryId).subscribe(

      data => {

        console.log(data);

      }

    );

    this.refreshPage();

  }

  refreshPage() {
    this.router.navigateByUrl('/admin-category-roster', {skipLocationChange: false}).then(() =>
      this._document.defaultView.location.reload());
  }

}
