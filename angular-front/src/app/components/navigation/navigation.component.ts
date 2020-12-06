import {Component, OnInit,Inject} from '@angular/core';
import { DOCUMENT } from '@angular/common';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit{

  constructor(@Inject(DOCUMENT) private _document: Document,private router: Router) { }

  refreshPage() {
    this.router.navigateByUrl('/', {skipLocationChange: false}).then(()=>
     this._document.defaultView.location.reload());
  }

  ngOnInit(): void {
  }

}
