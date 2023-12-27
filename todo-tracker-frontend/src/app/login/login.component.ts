import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  hide = true;

  credentials = {
    emailId: '',
    password: '',
  };

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    // console.log('inside login');
    if (this.credentials.emailId != '' && this.credentials.password != '') {
      // token generate
      // console.log(this.loginService.generateToken(this.credentials));
      this.loginService.generateToken(this.credentials).subscribe(
        (response: any) => {
          // success
          console.log(response);
          this.loginService.loginUser(response);
          // window.location.href="/dashboard"
          // this.router.navigateByUrl("/dashboard")
          this.router.navigate(['dashboard']);
        },
        (error) => {
          // failure
          console.log(error);
          // alert(errror.message);
        }
      );
    } else {
      console.log('Fields are empty');
    }
  }
}
