import { Component } from '@angular/core';
import { TodoDataService } from '../services/todo-data.service';
import { MatSnackBar } from '@angular/material/snack-bar';

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

  constructor(private todoDataService: TodoDataService, private _snackBar: MatSnackBar){}

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

  deleteTodo(id: any) {
    // Display a confirmation dialog
    const isConfirmed = window.confirm('Are you sure you want to delete this todo?');
  
    // Check if the user confirmed the deletion
    if (isConfirmed) {
      console.log(`Delete id ${id}`);
      this.todoDataService.deleteTodo(id).subscribe(
        response => {
          console.log(response);
          this._snackBar.open('Todo deleted!', 'Success', {
            duration: 3000,
          });
          this.refreshTodos();
        },
        error => {
          console.log(error);
        }
      );
    } else {
      console.log('Deletion canceled by user.');
    }
  }
  
}
