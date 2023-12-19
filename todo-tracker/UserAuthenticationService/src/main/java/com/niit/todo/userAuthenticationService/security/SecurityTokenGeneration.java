package com.niit.todo.userAuthenticationService.security;

import com.niit.todo.userAuthenticationService.domain.User;

public interface SecurityTokenGeneration {
    String createToken(User user);
}
