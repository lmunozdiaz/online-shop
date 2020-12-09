import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import { MatSelectCountryModule } from '@angular-material-extensions/select-country'; 
import {HttpClientModule} from '@angular/common/http';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService) { }
  emailAlreadyExists = false;
  user: User = new User();
  ngOnInit(): void {
  }

  generateOnetimeCode(emailId) {
    this.userService.getOneTimeCode(emailId).subscribe(
      data => {
        alert('One time code generated Successfully' + data);
      }
    );
  }
  
  onSubmit() {
    this.saveUser();
  }

  saveUser() {
    this.userService.createUser(this.user).subscribe(data => {
      console.log('data');
            console.log(data);

      // this.goToEmployeeList();
    },
      error => {
        if (error.status == '409') {
          console.error("User already exists");
          this.emailAlreadyExists = true;
        } else {
          alert('errpr');
        }
      });
  }
}
