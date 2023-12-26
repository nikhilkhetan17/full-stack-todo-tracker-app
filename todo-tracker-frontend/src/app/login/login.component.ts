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

  crdentials = {
    emailId: '',
    password: '',
  }


  onSubmit() {
    if(this.crdentials.emailId != '' && this.crdentials.password != '') {
      this.loginService.generateToken(this.crdentials).subscribe(
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
