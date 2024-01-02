package com.niit.todo.usertodoservice.proxy;

import com.niit.todo.usertodoservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mail-service", url = "localhost:8089")
public interface EmailProxy {
    @PostMapping("/mail/sendMail")
    public String sendMail(User user);

}
