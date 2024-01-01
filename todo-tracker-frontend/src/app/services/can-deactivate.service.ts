import { Injectable } from '@angular/core';
import { EditTodoComponent } from '../edit-todo/edit-todo.component';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CanDeactivateService {

  canDeactivate(
    component: EditTodoComponent,
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return component.canDeactivate ? component.canDeactivate() : true;
  }
}
