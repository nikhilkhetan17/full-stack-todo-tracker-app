import { Component } from '@angular/core';
import { TodoDataService } from '../services/todo-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Todo } from '../models/todo';
import { RegisterService } from '../services/register.service';

@Component({
  selector: 'app-edit-todo',
  templateUrl: './edit-todo.component.html',
  styleUrls: ['./edit-todo.component.css'],
})
export class EditTodoComponent {
  // todo: any = {};
  todo: Todo = {};
  minDate: Date = new Date();
  userName = '';
  mailBody = {};

  editStatus: boolean = true;
  canDeactivate() {
    if (!this.editStatus) {
      this.editStatus = confirm("Changes are not saved. Do you still want to leave?");
    }
    return this.editStatus;
  }

  constructor(
    private todoDataService: TodoDataService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private _snackBar: MatSnackBar,
    private registerService: RegisterService
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((params) => {
      let todoId = params.get('id') ?? 0;
      // console.log(todoId);
      this.todoDataService.getSingleTodo(todoId).subscribe((response: any) => {
        this.todo = response.todoList[0];
        console.log(response.todoList[0]);
        this.editStatus = false;
      });
    });

    // To get the username 
    this.registerService.getUserName().subscribe(
      response => {
        console.log(response);
        this.userName = response.split(' ')[0];
        // Initialize mailBody, after getting the userName
        this.mailBody = {
          emailId: sessionStorage.getItem('userEmail'), // Recipient mail id
          subject: 'Task completion status',
          msgBody: `
          Dear ${this.userName},
          Your task has been successfully completed.
      
          Happy task tracking!
          Best regards,
          Todo Tracker Team
          `
        };
      },
      error => {  
        console.log(error);
      }
    )
  }
  
  // ngOnInit() {
  //   this.todoDataService
  //     .getSingleTodo('2945bd55-e3ae-495d-9d56-15b183eecab6')
  //     .subscribe((response: any) => {
  //       console.log(response.todoList[0]);
  //     });
  // }

  updateTodo() {
    this.todoDataService.editTodo(this.todo).subscribe(
      (response: any) => {
        // this.todo = response;
        console.log(response);
        if(this.todo.todoCompleted === "Yes") {
          this.todoDataService.sendMail(this.mailBody).subscribe((resp)=>{
            console.log(resp);
          });
        }
        this.editStatus = true;
        this.router.navigate(['dashboard']);
        this._snackBar.open('Todo updated!', 'Success', {
          duration: 3000,
        });

      },
      (error) => {
        console.log(error);
      }
    );
  }
}
