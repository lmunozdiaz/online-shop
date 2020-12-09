import { Component, OnInit } from '@angular/core';
import {LoginForm} from "../../model/login-form";
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials: LoginForm = new LoginForm();
  user: User = new User();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {

    this.userService.login(this.credentials).subscribe(
      data => {

        this.user = data;

        localStorage.setItem("loggedIn", "true");
        localStorage.setItem("email", this.user.email);
        localStorage.setItem("role", this.user.role);

        console.log(data);

        this.router.navigateByUrl('/cart-details');

      }, error => {
        console.log(error)
        this.credentials.email = "";
        this.credentials.password = "";
      }
    );
  }
}
