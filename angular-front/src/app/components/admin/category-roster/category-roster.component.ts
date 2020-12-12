import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {CategoryService} from "../../../services/category.service";

@Component({
  selector: 'app-category-roster',
  templateUrl: './category-roster.component.html',
  styleUrls: ['./category-roster.component.css']
})
export class CategoryRosterComponent implements OnInit, AfterViewInit {

  dataSource = new MatTableDataSource();
  displayedColumns: string[] = ['name', 'categoryType', 'createTime', 'updateTime'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private categoryService: CategoryService) { }

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

}
