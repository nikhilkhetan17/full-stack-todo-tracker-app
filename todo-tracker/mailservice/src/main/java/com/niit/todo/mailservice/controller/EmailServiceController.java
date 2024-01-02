package com.niit.todo.mailservice.controller;

import com.niit.todo.mailservice.domain.User;
import com.niit.todo.mailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailServiceController {
    private EmailService emailService;

    @Autowired
    public EmailServiceController(EmailService emailService) {
        this.emailService = emailService;
    }

    //Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody User user) {
        System.out.println("ReceivedUser: " + user);
        System.out.println("hi,i'm here");
        String status = emailService.sendMail(user);
        System.out.println("EmailSendingStatus " + status);
        return status;
    }

}
