package com.niit.todo.userAuthenticationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Invalid credentials")
public class InvalidCredentialsException extends Exception{
}
