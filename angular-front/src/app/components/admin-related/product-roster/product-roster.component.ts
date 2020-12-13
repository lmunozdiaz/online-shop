import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ProductService} from "../../../services/product.service";
import {Product} from "../../../model/product";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-product-roster',
  templateUrl: './product-roster.component.html',
  styleUrls: ['./product-roster.component.css']
})
export class ProductRosterComponent implements OnInit, AfterViewInit {

  dataSource = new MatTableDataSource();
  displayedColumns: string[] = ['image', 'name', 'price', 'category', 'status', 'stock', 'createTime', 'updateTime','actions'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.fetchAllProducts();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  fetchAllProducts() {

    this.productService.getAllElevated().subscribe(

      data => {
        this.dataSource.data = data;
      }, error => {
        console.log(error);
      }
    );

  }

}
