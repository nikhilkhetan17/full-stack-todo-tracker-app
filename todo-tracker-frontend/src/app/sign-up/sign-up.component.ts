import { Component } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent {
  imageUrl: string = '../assets/images/signup.svg';
  hide = true;

  constructor(private registerService: RegisterService, private router: Router) { }

  register = {
    emailId: '',
    password: '',
    userName: '',
  };

  onSubmit() {
    console.log('hello');
    this.registerService.registerUser(this.register).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(["login"])
      },
      (err) => {
        console.log(err);
      }
    );
  }
  
}
