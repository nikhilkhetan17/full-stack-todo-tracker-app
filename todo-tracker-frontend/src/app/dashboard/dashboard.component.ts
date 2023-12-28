import { Component } from '@angular/core';
import { TodoDataService } from '../services/todo-data.service';

export class Todo {
  constructor(
    public todoName: string,
    public todoDescription: string,
    public targetDate: Date,
    public todoCompleted: string,
    public todoId: any,
    public priority: string
  ) {}
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  todos: Todo[] = [];

  constructor(private todoDataService: TodoDataService){}

  ngOnInit() {
    // this.todoDataService.getAllTodos().subscribe(
    //   (response: any) => {
    //     console.log(response);
    //     this.todos = response;
    //   },
    //   error => {
    //     console.log(error);
    //   }
    // )
    this.refreshTodos();
  }

  refreshTodos() {
    this.todoDataService.getAllTodos().subscribe(
      (response: any) => {
        console.log(response);
        this.todos = response;
      },
      error => {
        console.log(error);
      }
    )
  }

  deleteTodo(id:any) {
    console.log(`Delete id ${id}`);
    this.todoDataService.deleteTodo(id).subscribe(
      response => {
        console.log(response);
        this.refreshTodos();
      },
      error => {
        console.log(error);
      }
    )
  }
}
