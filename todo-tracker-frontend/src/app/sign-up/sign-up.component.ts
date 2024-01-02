import { Component } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../models/user';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent {
  imageUrl: string = '../assets/images/signup.svg';
  hide = true;
  loading = false; // Add loading variable

  // register = {
  //   emailId: '',
  //   password: '',
  //   userName: '',
  // };

  register: User = {};

  constructor(
    private registerService: RegisterService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  onSubmit() {
    this.loading = true; // Set loading to true when registration starts
    // console.log('hello');
    this.registerService.registerUser(this.register).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['login']);
        this._snackBar.open('User Registered Successfully!', 'Success', {
          duration: 3000,
        });
      },
      (err) => {
        console.log(err);
        this._snackBar.open('User Already Exists!', 'Error!', {
          duration: 3000,
        })
      }
    ).add(() => {
      this.loading = false; // Set loading to false when registration completes
    });
  }

}
