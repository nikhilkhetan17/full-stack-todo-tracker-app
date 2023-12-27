import { Component } from '@angular/core';
import { TodoDataService } from '../services/todo-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css'],
})
export class AddTodoComponent {
  todo: any = {};

  minDate: Date = new Date();

  constructor(
    private todoDataService: TodoDataService,
    private router: Router
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.todoDataService.addTodo(this.todo).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['dashboard']);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
