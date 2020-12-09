import {Component, Inject, OnInit} from '@angular/core';
import {DOCUMENT} from '@angular/common';
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  // to display dynamic user information;
  // if the user is logged in, then don't display the menu of buttons (sign in, sign up) ...
  // instead, show a logout button, and maybe their username.
  isLoggedIn: boolean = false;
  loggedInUser: User = new User();

  constructor(@Inject(DOCUMENT) private _document: Document, private router: Router,
              private userService: UserService) {
  }

  // TODO: this method validates whether a user is logged in.
  checkLogInStatus() {

    this.userService.isLoggedIn().subscribe(
      data => {
        this.isLoggedIn = data;
      }
    );

  }

  // TODO: this method will give us access to the user's username.
  getLoggedInUser() {

  }

  logoutUser() {

    this.userService.logout().subscribe(
      data => {
        this.router.navigate(['/login']);
      }, error => {
        console.log(error);
      }
    );

  }

  refreshPage() {
    this.router.navigateByUrl('/', {skipLocationChange: false}).then(() =>
      this._document.defaultView.location.reload());
  }

  ngOnInit(): void {
    this.checkLogInStatus();
  }

}
