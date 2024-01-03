//package com.niit.todo.usertodoservice.repository;
//
//import com.niit.todo.usertodoservice.domain.TodoList;
//import com.niit.todo.usertodoservice.domain.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class TodoRepositoryTest {
//    @Autowired
//    private UserTodoRepository userTodoRepository;
//    private TodoList todoList1,todoList2;
//    private User user;
//    List<TodoList> todoLists;
//    Date specificDate =null;
//    UUID specificUUID =null;
//    TodoRepositoryTest object = null;
//    @BeforeEach
//    void setUp(){
//        specificDate = new Date();
//         specificUUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
//         object = new TodoRepositoryTest();
//        todoList1=new TodoList(specificUUID,"At work","GOOD",specificDate,"yes","high");
//        todoList2=new TodoList(specificUUID,"practice","on the process",specificDate,"no","medium");
//        user=new User();
//        user.setEmailId("abc@gmail.com");
//        user.setPassword("12345");
//        user.setUserName("abc");
//        todoLists= Arrays.asList(todoList1,todoList2);
//        user.setTodoList(todoLists);
//    }
//    @AfterEach
//    void tearDown(){
//        specificDate = null;
//        specificUUID = null;
//        object = null;
//        todoList1=todoList2=null;
//        userTodoRepository.deleteAll();
//    }
//    @Test
//    void givenTodoSaveShouldReturnSavedTodoList(){
//        userTodoRepository.save(user);
//        User user1=userTodoRepository.findById(user.getEmailId()).get();
//        assertNotNull(user1);
//        assertEquals(user1.getEmailId(),user.getEmailId());
//    }
//    @Test
//    public void givenTodoDeleteShouldDeleteTodoList(){
//        userTodoRepository.insert(user);
//        User user1 = userTodoRepository.findById(user.getEmailId()).get();
//        userTodoRepository.delete(user1);
//        assertEquals(Optional.empty(), userTodoRepository.findById(user.getEmailId()));
//    }
//
//}
//
//
//
