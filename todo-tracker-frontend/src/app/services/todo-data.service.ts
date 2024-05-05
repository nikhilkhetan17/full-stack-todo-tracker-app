import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import { Todo } from '../dashboard/dashboard.component';
import { Todo } from '../models/todo';

@Injectable({
  providedIn: 'root',
})
export class TodoDataService {
  url = 'http://localhost:9000';

  constructor(private http: HttpClient) {}

  getAllTodos() {
    return this.http.get(`${this.url}/api/v2/user/todos`);
  }

  addTodo(todo: Todo) {
    return this.http.post(`${this.url}/api/v2/user/todo`, todo);
  }

  deleteTodo(id: any) {
    return this.http.delete(`${this.url}/api/v2/user/todo/${id}`);
  }

  getSingleTodo(id: any) {
    return this.http.get(`${this.url}/api/v2/user/get-todo/${id}`);
  }

  editTodo(todo: any) {
    return this.http.put(`${this.url}/api/v2/user/todo`, todo);
  }

  // ----------------------Archive----------------------

  saveToArchiveList(task: any) {
    return this.http.post(`${this.url}/api/v2/user/archiveTodo`, task);
  }

  getAllArchivedTodoList() {
    return this.http.get(`${this.url}/api/v2/user/archivedTodoList`);
  }

  deleteTaskFromArchivedTodoList(id: any) {
    return this.http.delete(`${this.url}/api/v2/user/archivedTodoList/${id}`);
  }

   // -------------------------------------------
   unarchieveTask(task: any) {
    // return this.http.post(this.baseUrl + "user/unarchiveTask", task, { headers });
    return this.http.post(`${this.url}/api/v2/user/unarchiveTask`, task);
  }

  // -----------------------------------------------------

  public sendMail(mailBody: object){
    return this.http.post(`http://localhost:8089/mail/sendStatus`, mailBody, {
      responseType: 'text',
    });
  }

}
