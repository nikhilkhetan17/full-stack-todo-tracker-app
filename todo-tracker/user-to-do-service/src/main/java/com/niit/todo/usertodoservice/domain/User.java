package com.niit.todo.usertodoservice.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
    @Id
    private String emailId;
    private String password;
    private String userName;
    private List<TodoList> todoList;
    private List<TodoList> archievedTodoList;

    public User(String emailId, String password, String userName, List<TodoList> todoList, List<TodoList> archievedTodoList) {
        this.emailId = emailId;
        this.password = password;
        this.userName = userName;
        this.todoList = todoList;
        this.archievedTodoList = archievedTodoList;
    }

    public User() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TodoList> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoList> todoList) {
        this.todoList = todoList;
    }

    public List<TodoList> getArchievedTodoList() {
        return archievedTodoList;
    }

    public void setArchievedTodoList(List<TodoList> archievedTodoList) {
        this.archievedTodoList = archievedTodoList;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", todoList=" + todoList +
                '}';
    }
}
