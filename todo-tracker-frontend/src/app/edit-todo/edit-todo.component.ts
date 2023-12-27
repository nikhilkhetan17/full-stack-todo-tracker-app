import { Component } from '@angular/core';
import { TodoDataService } from '../services/todo-data.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-todo',
  templateUrl: './edit-todo.component.html',
  styleUrls: ['./edit-todo.component.css'],
})
export class EditTodoComponent {
  todo: any = {};

  minDate: Date = new Date();

  constructor(
    private todoDataService: TodoDataService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((params) => {
      let todoId = params.get('id') ?? 0;
      this.todoDataService.getSingleTodo(todoId).subscribe((response: any) => {
        this.todo = response.todoList[0];
        console.log(response.todoList[0]);
        // this.editStatus = false;
      });
    });
  }
  // ngOnInit() {
  //   this.todoDataService
  //     .getSingleTodo('2945bd55-e3ae-495d-9d56-15b183eecab6')
  //     .subscribe((response: any) => {
  //       console.log(response.todoList[0]);
  //     });
  // }

  updateTodo() {
    this.todoDataService.editTodo(this.todo).subscribe((response: any) => {
      // this.todo = response;
      console.log(response);
      // this.editStatus = true;
      this.router.navigate(['dashboard']);
    });
  }
}
