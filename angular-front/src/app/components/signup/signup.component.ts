import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  generateOnetimeCode(emailId) {
   this.userService.getOneTimeCode(emailId).subscribe(
      data => {
       alert('One time code generated Successfully'+data);
      }
    );
  }
}
