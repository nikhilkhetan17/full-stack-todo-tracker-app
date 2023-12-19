package com.niit.todo.userAuthenticationService.service;

import com.niit.todo.userAuthenticationService.domain.User;
import com.niit.todo.userAuthenticationService.exception.InvalidCredentialsException;
import com.niit.todo.userAuthenticationService.exception.UserAlreadyExistsException;

public interface IUserService {
    User saveUser(User user) throws UserAlreadyExistsException;

    User getUserByEmailIdAndPassword(String emailId, String password) throws InvalidCredentialsException;
}
