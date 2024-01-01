import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  hide = true;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  // credentials = {
  //   emailId: '',
  //   password: '',
  // };

  credentials: User = {};

  constructor(
    private loginService: LoginService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  onSubmit() {
    if (this.credentials.emailId != '' && this.credentials.password != '') {
      // token generate
      // console.log(this.loginService.generateToken(this.credentials));
      this.loginService.generateToken(this.credentials).subscribe(
        (response: any) => {
          console.log(response);
          this.loginService.loginUser(response);
          // window.location.href="/dashboard"
          // this.router.navigateByUrl("/dashboard")
          this.router.navigate(['dashboard']);
          this._snackBar.open('Logged In Successfully!', 'Success', {
            duration: 3000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
        },
        (error) => {
          console.log(error);
          this._snackBar.open('Invalid Credentials!', 'Error!', {
            duration: 5000,
          });
          // alert(errror.message);
        }
      );
    }
  }
}
