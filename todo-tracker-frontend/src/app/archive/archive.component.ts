import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TodoDataService } from '../services/todo-data.service';
import { Todo } from '../models/todo';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent {
  todos: Todo[] = [];
  categoryFilter: string = "";

  constructor(private todoDataService: TodoDataService, private _snackBar: MatSnackBar){}

  ngOnInit() {
    this.getAllArchiveTodoList();
  }

   getAllArchiveTodoList() {
    this.todoDataService.getAllArchivedTodoList().subscribe({
      next: (data: any) => {
        this.todos = data;
        console.log(data);
      },
      error: (error) => {
        alert('Failed to Fetch Todos Due to Server Error!!');
      }
    });
  }


  deleteTodoArchive(id: any) {
    // Display a confirmation dialog
    const isConfirmed = window.confirm('Are you sure you want to delete this todo?');
  
    // Check if the user confirmed the deletion
    if (isConfirmed) {
      console.log(`Delete id ${id}`);
      this.todoDataService.deleteTaskFromArchivedTodoList(id).subscribe(
        response => {
          console.log(response);
          this._snackBar.open('Todo deleted!', 'Success', {
            duration: 3000,
          });
          this.getAllArchiveTodoList();
        },
        error => {
          console.log(error);
        }
      );
    } else {
      console.log('Deletion canceled by user.');
    }
  }

  unarchiveTask(task: any) {
    this.todoDataService.unarchieveTask(task).subscribe(
      (response) => {
        console.log('Task unarchieved successfully:', response);

        // Update the taskList
        this.getAllArchiveTodoList(); 
        this._snackBar.open('Todo Unarchived!', 'Success', {
          duration: 3000,
        });
        // this.stateService.toggleArchive()
        // this.router.navigateByUrl("/taskList")
      },
      (error) => {
        console.error('Error deleting task:', error);
      }
    );
  }

  onFilter(filter: string) {
    this.categoryFilter = filter;
    this.todoDataService.getAllArchivedTodoList().subscribe({
      next: (data: any) => {
        if (this.categoryFilter === "All") {
          // If the filter is "All," set todos to the entire data
          this.todos = data;
        } else {
          // Filter todos based on the priority
          this.todos = data.filter((todo: any) => todo.priority === this.categoryFilter);
        }
      }
    })
  }
  
}
