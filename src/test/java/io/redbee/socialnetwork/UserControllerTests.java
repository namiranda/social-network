package io.redbee.socialnetwork;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.redbee.socialnetwork.users.User;
import io.redbee.socialnetwork.users.UserController;
import io.redbee.socialnetwork.users.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserDao userDao;

    @InjectMocks
    UserController userController;

    List<User> users;
    User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers() throws Exception {
       users = new ArrayList<>();
       users.add(new User(1,"noe@gmail", "adejnafwf", "CREATED", LocalDateTime.now(), "system", LocalDateTime.now(), "system"));
       users.add(new User(2,"otro@gmail", "adejntwf", "CREATED", LocalDateTime.now(), "system", LocalDateTime.now(), "system"));

       when(userDao.get()).thenReturn(users);

       this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
               .andExpect(status().isOk())
               .andDo(print());

    }
}