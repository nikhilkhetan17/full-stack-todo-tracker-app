package com.niit.todo.mailservice.service;

import com.niit.todo.mailservice.domain.User;

public interface EmailService {
    String sendMail(User user);

}
