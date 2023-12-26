import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  url = 'http://localhost:9000';

  constructor(private http: HttpClient) { }

  getAllTodos() {
    return this.http.get(`${this.url}/api/v2/user/todos`)
  }

  deleteTodo(id: any) {
    return this.http.delete(`${this.url}/api/v2/user/todo/${id}`)
  }
}
