//package com.niit.todo.userAuthenticationService.controller;
//
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.niit.todo.userAuthenticationService.domain.User;
//import com.niit.todo.userAuthenticationService.exception.UserAlreadyExistsException;
//import com.niit.todo.userAuthenticationService.service.UserServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//public class UserControllerTest
//{
//    private MockMvc mockMvc;
//    @Mock
//    private UserServiceImpl userService;
//    @InjectMocks
//    private UserController userController;
//    private User user;
//    @BeforeEach
//    public void setUp() throws Exception{
//        user=new User("ABC@gmail.com","12345");
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//     @AfterEach
//    public void tearDown() throws Exception{
//       user=null;
//     }
//     @Test
//    public void givenUserToSaveReturnUserSuccess() throws Exception{
//         when(userService.saveUser(any())).thenReturn(user);
//        mockMvc.perform(post("/api/v1/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user)))
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).saveUser(any());
//    }
//
//    @Test
//    public void givenUserToSaveReturnUserFailure() throws Exception{
//        when(userService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
//                       .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user)))
//               .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
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
//
//
//
//
