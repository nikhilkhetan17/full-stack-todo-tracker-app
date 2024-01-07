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
        // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            // Setting up necessary details
            mailMessage.setFrom(senderMail);
            mailMessage.setTo(user.getEmailId());

            String body = "Dear " + user.getUserName() + ",\n\n";
            body += "Welcome to Todo Tracker! We are thrilled to have you on board.\n";
            body += "Thank you for registering and choosing us to help you stay organized.\n\n";
            body += "Happy task tracking!\n\n";
            body += "Best regards,\nTodo Tracker Team";

            mailMessage.setSubject("Welcome to Todo Tracker!");
            mailMessage.setText(body);
            // Sending the mail
            javaMailSender.send(mailMessage);
            System.out.println("im here1");
            return "Mail Sent Successfully...";
        }
        // Catch block to handle the exceptions
        catch (Exception ex) {
            System.out.println(ex);
            return "Error in sending mail";
        }

    }

    @Override
    public String sendConformationStatus(User user) {
        try {
            // creating a simple mail message
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //Setting up necessary details
            simpleMailMessage.setFrom(senderMail);
            simpleMailMessage.setTo(user.getEmailId()); // Recipient mail id
            simpleMailMessage.setText(user.getMsgBody());
            simpleMailMessage.setSubject(user.getSubject());

            // Sending the mail
            javaMailSender.send(simpleMailMessage);
            return "Mail status sent Successfully...";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e);
            return "Error while Sending Mail status";

        }
    }

}

