package com.niit.todo.userAuthenticationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "User already present")
public class UserAlreadyExistsException extends Exception{
}
