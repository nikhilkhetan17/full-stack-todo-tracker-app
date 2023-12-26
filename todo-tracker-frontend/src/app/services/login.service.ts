import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url= 'http://localhost:9000';

  constructor(private http: HttpClient) { }

  generateToken(credentials: any) {
    return this.http.post(`${this.url}/api/v1/login`, credentials, {responseType: 'text'})
  }

  loginUser(token: any) {
    sessionStorage.setItem('token', token);
    return true;
  }

  isLoggedIn() {
    let token = sessionStorage.getItem('token');
    if(token == undefined || token == "") {
      return false;
    } else {
      return true;
    }
  }

  logout() {
    sessionStorage.removeItem('token');
    return true;
  }

  // for getting the token
  // getToken() {
  //   return sessionStorage.getItem('token');
  // }
}
