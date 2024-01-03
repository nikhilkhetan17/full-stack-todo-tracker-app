//package com.niit.todo.usertodoservice.service;
//
//
//import com.niit.todo.usertodoservice.domain.TodoList;
//import com.niit.todo.usertodoservice.domain.User;
//import com.niit.todo.usertodoservice.repository.UserTodoRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class)
//
//public class TodoServiceImplTest {
//    @Mock
//    private UserTodoRepository userTodoRepository;
//    @InjectMocks
//    private TodoServiceImpl todoService;
//   private TodoList todoList1,todoList2;
//    List<TodoList> todoLists;
//    User user;
//    Date specificDate = new Date(2023 - 1900, 0, 1);
//    UUID specificUUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
//
//    @BeforeEach
//    void setUp(){
//        todoList1=new TodoList(specificUUID,"At work","GOOD",specificDate,"Yes","High");
//        todoList2=new TodoList(specificUUID,"practice","on the process",specificDate,"No","Medium");
//       user=new User();
//        user.setEmailId("abc@gmail.com");
//        user.setPassword("12345");
//        user.setUserName("abc");
//        user.setTodoList(todoLists);
//    }
//    @AfterEach
//    void tearDown(){
//        todoList1=null;
//        todoList2=null;
//        user=null;
//    }
//    @Test
//    public void getAllUserTodoFromWishTodoListSuccess() throws Exception{
//        when(userTodoRepository.findById(anyString())).thenReturn(Optional.ofNullable(user));
//        when(userTodoRepository.findById(anyString())).thenReturn(Optional.of(user));
//        assertEquals(todoLists,todoService.getAllTodos(user.getEmailId()));
//        verify(userTodoRepository,times(2)).findById(anyString());
//    }
//}
//
//
//
