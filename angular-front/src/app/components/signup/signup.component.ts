import { Component, OnInit,Inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import { MatSelectCountryModule } from '@angular-material-extensions/select-country';
import { HttpClientModule } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Router} from "@angular/router";

export interface DialogData {
  msg: string;
}


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService,public dialog: MatDialog,private router: Router) {
    this.errors = [];
  }
  emailAlreadyExists = false;
  errors: string[];
  msg: string[];

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

  onSubmit(form: NgForm) {
    this.errors = [];
    this.msg = [];
    this.saveUser(form);
  }

  saveUser(form: NgForm) {
    this.userService.createUser(this.user).subscribe(data => {

    },
      error => {
        if (error.status == '409') {
          this.emailAlreadyExists = false;
          this.errors.push(error.error);
        }else if (error.status == '200') {
          this.msg.push(error.text);
          form.resetForm();
          this.openDialog(error.error.text);
        }
        else {
          this.errors.push(error.error);
        }
      });
  }

  openDialog(msg1:string): void {
    const dialogRef = this.dialog.open(DialogElementsExampleDialog, {
     height: '200px',
     width: '600px',
     data: {msg:msg1}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.router.navigateByUrl(`/`);
    });
  }
}
@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: '../../components/dialog-elements-example-dialog.html',
})
export class DialogElementsExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogElementsExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}