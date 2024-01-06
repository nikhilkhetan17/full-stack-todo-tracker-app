import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

/*
 HTTP interceptor is responsible for adding an Authorization header with a Bearer token to outgoing requests, based on the token retrieved from the LoginService.
*/
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private loginService: LoginService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let newReq = req;
    let token = this.loginService.getToken();
    console.log("Interceptor", token);

    if(token != null) {
      newReq = newReq.clone({setHeaders: {Authorization: `Bearer ${token}`}})
    }

    return next.handle(newReq);
      

  }
}
