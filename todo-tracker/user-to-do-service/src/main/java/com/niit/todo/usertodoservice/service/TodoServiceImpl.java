package com.niit.todo.usertodoservice.service;

import com.niit.todo.usertodoservice.domain.TodoList;
import com.niit.todo.usertodoservice.domain.User;
import com.niit.todo.usertodoservice.exception.TodoAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.TodoNotFoundException;
import com.niit.todo.usertodoservice.exception.UserAlreadyExistsException;
import com.niit.todo.usertodoservice.exception.UserNotFoundException;
import com.niit.todo.usertodoservice.proxy.EmailProxy;
import com.niit.todo.usertodoservice.proxy.UserProxy;
import com.niit.todo.usertodoservice.repository.UserTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoServiceImpl implements ITodoService {

    private UserTodoRepository userTodoRepository;
    private UserProxy userProxy;
    private EmailProxy emailProxy;

    @Autowired
    public TodoServiceImpl(UserTodoRepository userTodoRepository, UserProxy userProxy, EmailProxy emailProxy) {
        this.userTodoRepository = userTodoRepository;
        this.userProxy = userProxy;
        this.emailProxy = emailProxy;
    }

    //  Register a user
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (userTodoRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        String sendMail = emailProxy.sendMail(user);
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
                if(todoList.getTodoCompleted() != null)
                    todoListFields.setTodoCompleted(todoList.getTodoCompleted());
                if(todoList.getPriority() != null)
                    todoListFields.setPriority(todoList.getPriority());
                flag = true;
            }
        }
        if (!flag) {
            throw new TodoNotFoundException();
        }
        user.setTodoList(getTodoLists);
        return userTodoRepository.save(user);
    }

    @Override
    public String getUserName(String emailId) throws UserNotFoundException {
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        return userTodoRepository.findById(emailId).get().getUserName();
    }


    @Override
    public User retrieveSingleTodo(String emailId, UUID todoID) throws UserNotFoundException,TodoNotFoundException {
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }


        User user = userTodoRepository.findById(emailId).get();
        List<TodoList> todoList = user.getTodoList();

        // Search for the todo with the specified todoID
        //  Uses Java Stream API to filter the TodoList based on the specified todoID.
        Optional<TodoList> optionalTodo = todoList.stream()
                .filter(todo -> todo.getTodoId().equals(todoID))
                .findFirst();

        if (optionalTodo.isEmpty()) {
            throw new TodoNotFoundException();
        }

        // setting the todoList property of the user object with a list containing a single element obtained from an Optional object.
        user.setTodoList(Collections.singletonList(optionalTodo.get()));
        return user;
    }

    //    ----------------------------------Archive-------------------------------------------

    @Override
    public User saveTodoToArchivedList(TodoList task, String emailId) throws TodoAlreadyExistsException, UserNotFoundException, TodoNotFoundException {
//        It checks whether a user with the provided emailId exists in the repository. If not, it throws a UserNotFoundException.
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

//        If the user exists, it retrieves the User object from the repository based on the provided emailId.
        User user = userTodoRepository.findById(emailId).get();

//        It checks if the provided task already exists in the user's archived todo list.
        if (user.getArchievedTodoList() != null && user.getArchievedTodoList().stream().anyMatch(t -> t.getTodoId().equals(task.getTodoId()))) {
            throw new TodoAlreadyExistsException();
        }

        // If the archived list is currently null, it creates a new list with the task; otherwise, it appends the task to the existing list.
        if (user.getArchievedTodoList() == null) {
            user.setArchievedTodoList(Collections.singletonList(task));
        } else {
            List<TodoList> tasks = new ArrayList<>(user.getArchievedTodoList());
            tasks.add(task);
            user.setArchievedTodoList(tasks);
        }
        //save new task into archiveList
        userTodoRepository.save(user);

        //code to remove the task from taskList after adding to archivedTaskList
        UUID todoId = task.getTodoId();
        User updateUser = deleteTodo(emailId, todoId);

//        Finally, it returns the updated User object, which now includes the newly added task in the archived todo list and the task removed from the todo list.
        return updateUser;
    }

    @Override
    public List<TodoList> getAllTodosFromArchievedList(String emailId) throws UserNotFoundException {
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTodoRepository.findById(emailId).get();

        // Return the list of tasks in the user's archive list
        return user.getArchievedTodoList();

    }

    @Override
    public User deleteTodoFromArchivedTodoList(String emailId, UUID taskId) throws TodoNotFoundException, UserNotFoundException {
        if (userTodoRepository.findById(emailId).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTodoRepository.findById(emailId).get();

        List<TodoList> tasks = user.getArchievedTodoList();
        boolean taskIdIsPresent = false;

        // Check if the provided trackId exists in the user's track list
        for (TodoList task : tasks) {
            if (task.getTodoId().equals(taskId)){
                taskIdIsPresent = true;
                tasks.remove(task);
                break;
            }
        }

        if (!taskIdIsPresent) {
            throw new TodoNotFoundException();
        }

        user.setArchievedTodoList(tasks);
        return userTodoRepository.save(user);
    }
}


/*
Collections.singletonList(task) is a method from the
java.util.Collections utility class in Java. This method returns an immutable list containing only
the specified element. In this case, it creates a list with a single element,
which is the task provided as an argument.
 */