package com.niit.todo.usertodoservice.service;

import com.niit.todo.usertodoservice.domain.TodoList;
import com.niit.todo.usertodoservice.domain.User;
import com.niit.todo.usertodoservice.exception.TodoAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.TodoNotFoundException;
import com.niit.todo.usertodoservice.exception.UserAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.UserNotFoundException;
import com.niit.todo.usertodoservice.proxy.UserProxy;
import com.niit.todo.usertodoservice.repository.UserTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements ITodoService {

    private UserTodoRepository userTodoRepository;
    private UserProxy userProxy;

    @Autowired
    public TodoServiceImpl(UserTodoRepository userTodoRepository, UserProxy userProxy) {
        this.userTodoRepository = userTodoRepository;
        this.userProxy = userProxy;
    }

    //  Register a user
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (userTodoRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User savedUser = userTodoRepository.save(user);
        if(!(savedUser.getEmailId().isEmpty())) {
            ResponseEntity<?> responseEntity = userProxy.saveUser(user);
            System.out.println(responseEntity.getBody());
        }
        return savedUser;
    }

    // save todo list
    @Override
    public User saveTodo(TodoList todoList, String emailId) throws TodoNotFoundException, UserNotFoundException {
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTodoRepository.findById(emailId).get();

        if (user.getTodoList() == null) {
            user.setTodoList(Arrays.asList(todoList));
        } else {
            List<TodoList> todoList1 = user.getTodoList();
            todoList1.add(todoList);
            user.setTodoList(todoList1);
        }
        return userTodoRepository.save(user);
    }

    // get all todos
    @Override
    public List<TodoList> getAllTodos(String emailId) throws TodoNotFoundException, UserNotFoundException {
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userTodoRepository.findById(emailId).get().getTodoList();
    }

    // delete a todo
    @Override
    public User deleteTodo(String emailId, UUID todoId) throws TodoNotFoundException, UserNotFoundException {
        boolean emailIdIsPresent = false;
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTodoRepository.findById(emailId).get();
        List<TodoList> todoList = user.getTodoList();

        emailIdIsPresent = todoList.removeIf(x -> x.getTodoId().equals(todoId));
        if (!emailIdIsPresent) {
            throw new TodoNotFoundException();
        }
        user.setTodoList(todoList);
        return userTodoRepository.save(user);

    }

    // update a todo
    @Override
    public User updateTodo(String emailId, TodoList todoList) throws UserNotFoundException, TodoNotFoundException {
        boolean flag = false;
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTodoRepository.findById(emailId).get();
        List<TodoList> getTodoLists = user.getTodoList();

        Iterator<TodoList> todoListIterator = getTodoLists.iterator();
        while (todoListIterator.hasNext()) {
            TodoList todoListFields = todoListIterator.next();
            if (todoListFields.getTodoId().equals(todoList.getTodoId())) {
                if (todoList.getTodoName() != null)
                    todoListFields.setTodoName(todoList.getTodoName());
                if (todoList.getTodoDescription() != null)
                    todoListFields.setTodoDescription(todoList.getTodoDescription());
                if (todoList.getTargetDate() != null)
                    todoListFields.setTargetDate(todoList.getTargetDate());
                Boolean isCompleted = todoList.isTodoCompleted();
                if (isCompleted != null)
                    todoListFields.setTodoCompleted(todoList.isTodoCompleted());
                flag = true;
            }
        }
        if (!flag) {
            throw new TodoNotFoundException();
        }
        user.setTodoList(getTodoLists);
        return userTodoRepository.save(user);
    }
}
