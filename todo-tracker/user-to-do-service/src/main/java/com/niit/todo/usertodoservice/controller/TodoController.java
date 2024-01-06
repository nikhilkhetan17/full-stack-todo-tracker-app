package com.niit.todo.usertodoservice.controller;

import com.niit.todo.usertodoservice.domain.TodoList;
import com.niit.todo.usertodoservice.domain.User;
import com.niit.todo.usertodoservice.exception.TodoAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.TodoNotFoundException;
import com.niit.todo.usertodoservice.exception.UserAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.UserNotFoundException;
import com.niit.todo.usertodoservice.service.ITodoService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;


@RestController
@RequestMapping("/api/v2")
//@CrossOrigin(origins = "*")
public class TodoController {

    private ITodoService iTodoService;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public TodoController(ITodoService iTodoService) {
        this.iTodoService = iTodoService;
    }

    // get email from token
    private String getEmailIdFromClaims(HttpServletRequest request) {
        System.out.println("header " + request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("Email ID from claims :: " + claims.getSubject());
        return claims.getSubject();
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(iTodoService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error in registering user!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/user/todo")
    public ResponseEntity<?> saveTodoList(@RequestBody TodoList todoList, HttpServletRequest request) throws UserNotFoundException {
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.saveTodo(todoList, id), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error in saving todo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/todos")
    public ResponseEntity<?> getAllTodosList(HttpServletRequest request) throws UserNotFoundException {
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.getAllTodos(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Cannot fetch to-dos!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/user/todo/{todoId}")
    public ResponseEntity<?> deleteATodo(@PathVariable UUID todoId, HttpServletRequest request) throws TodoNotFoundException, UserNotFoundException {
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.deleteTodo(id, todoId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TodoNotFoundException e) {
            throw new TodoNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Could not delete todo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/user/todo")
    public ResponseEntity<?> updateATodo(@RequestBody TodoList todoList, HttpServletRequest request) throws UserNotFoundException, TodoNotFoundException {

        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.updateTodo(id, todoList), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TodoNotFoundException e) {
            throw new TodoNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Could not update todo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/getUsername")
    public ResponseEntity<?> getUsername(HttpServletRequest request) throws UserNotFoundException {
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.getUserName(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Cannot get Username", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/get-todo/{todoId}")
    public ResponseEntity<?> getATodo(@PathVariable UUID todoId, HttpServletRequest request) throws UserNotFoundException {
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.retrieveSingleTodo(id, todoId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Cannot get a todo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //    ------------------------Archive-------------------------------

    @PostMapping("user/archiveTodo") //------------
    public ResponseEntity<?> saveTaskToArchivedList(@RequestBody TodoList task, HttpServletRequest request) throws TodoAlreadyExistsException, UserNotFoundException, TodoNotFoundException {
        // add a task to a specific user archivedTaskList, return 201 status if task is saved else 500 status
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.saveTodoToArchivedList(task, id), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TodoAlreadyExistsException e) {
            throw new TodoAlreadyExistsException();
        } catch (TodoNotFoundException e) {
            throw new TodoNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("user/archivedTodoList") //------------
    public ResponseEntity<?> getAllTasksFromArchivedList(HttpServletRequest request) {
        // display all the task of a specific user from archivedTaskList, extract user id from claims,
        // return 200 status if user is saved else 500 status
        try {

            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.getAllTodosFromArchievedList(id), HttpStatus.OK);
        } catch (Exception e) {
//            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Cannot fetch Archived todo list!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("user/archivedTodoList/{todoId}") //-----------
    public ResponseEntity<?> deleteTaskFromArchivedList(@PathVariable UUID todoId, HttpServletRequest request) throws TodoNotFoundException, UserNotFoundException {

        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(iTodoService.deleteTodoFromArchivedTodoList(id, todoId), HttpStatus.OK);
        } catch (TodoNotFoundException e) {
            throw new TodoNotFoundException();
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

}
