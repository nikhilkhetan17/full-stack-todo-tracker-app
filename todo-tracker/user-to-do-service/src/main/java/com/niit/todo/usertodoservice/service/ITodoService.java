package com.niit.todo.usertodoservice.service;

import com.niit.todo.usertodoservice.domain.TodoList;
import com.niit.todo.usertodoservice.domain.User;
import com.niit.todo.usertodoservice.exception.TodoAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.TodoNotFoundException;
import com.niit.todo.usertodoservice.exception.UserAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.UserNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;

public interface ITodoService {
    User registerUser(User user) throws UserAlreadyExistsException;
    User saveTodo(TodoList todoList, String emailId) throws TodoNotFoundException, UserNotFoundException;
    List<TodoList> getAllTodos(String emailId) throws TodoNotFoundException , UserNotFoundException;
    User deleteTodo(String emailId, UUID todoId) throws TodoNotFoundException, UserNotFoundException;
    User updateTodo(String emailId, TodoList todoList) throws UserNotFoundException, TodoNotFoundException;

    String getUserName(String emailId) throws UserNotFoundException;

    User retrieveSingleTodo(String emailId, UUID todoID) throws UserNotFoundException, TodoNotFoundException;
}
