import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private loginService: LoginService, private router: Router) {}

  credentials = {
    emailId: '',
    password: '',
  }


  onSubmit() {
    if(this.credentials.emailId != '' && this.credentials.password != '') {
      this.loginService.generateToken(this.credentials).subscribe(
        response => {
          console.log(response);
          this.loginService.loginUser(response);
          this.router.navigate(['dashboard'])
        },
        error => {
          console.log(error);
        }
      )
    }
  }

}
