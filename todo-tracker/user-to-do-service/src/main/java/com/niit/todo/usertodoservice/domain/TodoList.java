package com.niit.todo.usertodoservice.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class TodoList {
    @Id
    private UUID todoId = UUID.randomUUID();
    private String todoName;
    private String todoDescription;
    private Date targetDate;
    private String todoCompleted;
    private String priority;

    public TodoList(UUID todoId, String todoName, String todoDescription, Date targetDate, String todoCompleted, String priority) {
        this.todoId = todoId;
        this.todoName = todoName;
        this.todoDescription = todoDescription;
        this.targetDate = targetDate;
        this.todoCompleted = todoCompleted;
        this.priority = priority;
    }

    public TodoList() {
    }

    public UUID getTodoId() {
        return todoId;
    }

    public void setTodoId(UUID todoId) {
        this.todoId = todoId;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getTodoCompleted() {
        return todoCompleted;
    }

    public void setTodoCompleted(String todoCompleted) {
        this.todoCompleted = todoCompleted;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "todoId='" + todoId + '\'' +
                ", todoName='" + todoName + '\'' +
                ", todoDescription='" + todoDescription + '\'' +
                ", targetDate=" + targetDate +
                ", todoCompleted=" + todoCompleted +
                '}';
    }
}
