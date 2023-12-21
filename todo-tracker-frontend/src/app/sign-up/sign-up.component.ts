import { Component } from '@angular/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  imageUrl: string = "../assets/images/signup.svg"

  onSubmit() {
    console.log("hello");
  }

}
