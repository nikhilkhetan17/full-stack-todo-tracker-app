package com.niit.todo.userAuthenticationService.service;

import com.niit.todo.userAuthenticationService.domain.User;
import com.niit.todo.userAuthenticationService.exception.InvalidCredentialsException;
import com.niit.todo.userAuthenticationService.exception.UserAlreadyExistsException;
import com.niit.todo.userAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        System.out.println(user);
        return userRepository.save(user);

    }

    @Override
    public User getUserByEmailIdAndPassword(String emailId, String password) throws InvalidCredentialsException {
        User loggedUser = userRepository.findByEmailIdAndPassword(emailId, password);
        if (loggedUser == null) {
            throw new InvalidCredentialsException();
        }
        return loggedUser;
    }
}

