import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url = 'http://localhost:9000';

  constructor(private http: HttpClient) {}

  // calling the server to generate token
  generateToken(credentials: any) {
    return this.http.post(`${this.url}/api/v1/login`, credentials, {
      responseType: 'text',
    });
  }

  // to login user
  loginUser(token: any) {
    sessionStorage.setItem('token', token);
    return true;
  }

  // to check user is logged in or not
  isLoggedIn() {
    let token = sessionStorage.getItem('token');
    if (token == undefined || token === '' || token == null) {
      return false;
    } else {
      return true;
    }
  }

  // for logout the user
  logout() {
    sessionStorage.removeItem('token');
    return true;
  }

  // for getting the token
  getToken() {
    return sessionStorage.getItem('token');
  }
}
