package com.niit.todo.usertodoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Todo already exists")
public class TodoAlreadyExistsException extends Exception {
}
