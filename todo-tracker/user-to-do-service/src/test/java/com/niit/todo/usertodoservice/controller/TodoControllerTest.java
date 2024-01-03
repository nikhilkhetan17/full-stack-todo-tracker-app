//package com.niit.todo.usertodoservice.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.niit.todo.usertodoservice.domain.TodoList;
//import com.niit.todo.usertodoservice.domain.User;
//import com.niit.todo.usertodoservice.exception.UserAlreadyExistsException;
//import com.niit.todo.usertodoservice.service.TodoServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//public class TodoControllerTest {
//     private MockMvc mockMvc;
//     @Mock
//      private TodoServiceImpl todoService;
//     @InjectMocks
//     private TodoController todoController;
//    // private TodoList todoList1,todoList2;
//    private TodoList todoList1,todoList2;
//     List<TodoList> todoLists;
//     User user;
//    Date specificDate = new Date(2023 - 1900, 0, 1);
//    UUID specificUUID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
//
//    @BeforeEach
//    void setUp(){
//
//        todoList1=new TodoList(specificUUID,"At work","GOOD",specificDate,"yes","high");
//        todoList2=new TodoList(specificUUID,"practice","on the process",specificDate,"no","medium");
//        todoLists= Arrays.asList(todoList1,todoList2);
//
//        user=new User();
//        user.setEmailId("abc@gmail.com");
//        user.setPassword("12345");
//        user.setUserName("abc");
//        user.setTodoList(todoLists);
//        mockMvc=MockMvcBuilders.standaloneSetup(todoController).build();
//    }
//
//    @AfterEach
//    void tearDown(){
//        user=null;
//    }
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        when(todoService.registerUser(any())).thenReturn(user);
//        mockMvc.perform(post("/api/v2/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user)))
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).registerUser(any());
//
//    }
//    @Test
//    public void registerUserFailure() throws Exception {
//        when(todoService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
//        mockMvc.perform(post("/api/v2/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user)))
//                .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    private static String jsonToString(final Object ob) throws JsonProcessingException {
//        String result;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            result = jsonContent;
//        } catch(JsonProcessingException e) {
//            result = "JSON processing error";
//        }
//
//        return result;
//    }
//}
