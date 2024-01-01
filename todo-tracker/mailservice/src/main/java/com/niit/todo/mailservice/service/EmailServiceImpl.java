package com.niit.todo.mailservice.service;


import com.niit.todo.mailservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String senderMail;

    @Override
    public String sendMail(User user) {
        System.out.println("im here");
        // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            // Setting up necessary details
            mailMessage.setFrom(senderMail);
            mailMessage.setTo(user.getEmailId());
            mailMessage.setText("thanks for registoring");
            mailMessage.setSubject("mail for registration");
            // Sending the mail
            javaMailSender.send(mailMessage);
            System.out.println("im here1");
        }
        // Catch block to handle the exceptions
        catch (Exception exception) {
            System.out.println(exception);
        }
        return "Mail Sent Successfully...";
    }

}
