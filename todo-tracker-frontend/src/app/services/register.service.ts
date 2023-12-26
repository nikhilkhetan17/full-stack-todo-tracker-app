import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  url = 'http://localhost:9000';

  constructor(private http:HttpClient) { }

  registerUser(details:any){
    return this.http.post(`${this.url}/api/v2/register`, details);
  }

  // getUserName() {
  //   return this.http.get(`${this.url}/api/v2/user/getUsername`, {responseType: 'text'});
  // }
}
