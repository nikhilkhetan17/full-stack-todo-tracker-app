package com.niit.todo.userAuthenticationService.controller;


import com.niit.todo.userAuthenticationService.domain.User;
import com.niit.todo.userAuthenticationService.exception.InvalidCredentialsException;
import com.niit.todo.userAuthenticationService.exception.UserAlreadyExistsException;
import com.niit.todo.userAuthenticationService.security.SecurityTokenGeneration;
import com.niit.todo.userAuthenticationService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {
    private ResponseEntity<?> responseEntity;
    private IUserService iUserService;
    private SecurityTokenGeneration securityTokenGeneration;

    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGeneration securityTokenGeneration) {
        this.iUserService = iUserService;
        this.securityTokenGeneration = securityTokenGeneration;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
//        return new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
        try {
            responseEntity = new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException {
        User rettrivedUser = iUserService.getUserByEmailIdAndPassword(user.getEmailId(), user.getPassword());
        if (rettrivedUser == null) {
            throw new InvalidCredentialsException();
        }
        String object = securityTokenGeneration.createToken(user);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
