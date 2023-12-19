package com.niit.todo.usertodoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND , reason = "Todo not found")
public class TodoNotFoundException extends Exception {
}
