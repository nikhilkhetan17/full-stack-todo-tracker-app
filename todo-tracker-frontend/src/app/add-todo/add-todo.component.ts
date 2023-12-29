import { Component } from '@angular/core';
import { TodoDataService } from '../services/todo-data.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css'],
})
export class AddTodoComponent {
  todo: any = {
    todoCompleted: 'No',
  };

  minDate: Date = new Date();

  constructor(
    private todoDataService: TodoDataService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.todoDataService.addTodo(this.todo).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['dashboard']);
        this._snackBar.open('Todo added!', 'Success', {
          duration: 3000,
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
