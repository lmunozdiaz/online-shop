import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {ProductService} from "../../../services/product.service";
import {Product} from "../../../model/product";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Router} from "@angular/router";
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-product-roster',
  templateUrl: './product-roster.component.html',
  styleUrls: ['./product-roster.component.css']
})
export class ProductRosterComponent implements OnInit, AfterViewInit {

  dataSource = new MatTableDataSource();
  displayedColumns: string[] = ['image', 'name', 'price', 'categoryType',
    'status', 'stock','action'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private productService: ProductService, private router: Router,
              @Inject(DOCUMENT) private _document: Document) { }

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

  onDelete(product: Product) {

    this.productService.deleteProduct(product.id).subscribe(

      data => {

        console.log(data);

      }

    );

    this.refreshPage();

  }

  refreshPage() {
    this.router.navigateByUrl('/admin-product-roster', {skipLocationChange: false}).then(() =>
      this._document.defaultView.location.reload());
  }

}
