import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { RegisterService } from '../services/register.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  // public loggedIn = false;
  userName = '';

  constructor(public loginService: LoginService, private registerService: RegisterService){}

  // :void
  ngOnInit() {
    // this.loggedIn = this.loginService.isLoggedIn();

     // to get the username 
     this.registerService.getUserName().subscribe(
      response => {
        console.log(response);
        this.userName = response.split(' ')[0];
      },
      error => {  
        console.log(error);
      }
    )
  }

  logoutUser() {
    this.loginService.logout();
    location.reload();
  }

}
