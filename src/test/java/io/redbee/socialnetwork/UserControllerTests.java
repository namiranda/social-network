package io.redbee.socialnetwork;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.socialnetwork.feeds.posts.Post;
import io.redbee.socialnetwork.users.User;
import io.redbee.socialnetwork.users.UserController;
import io.redbee.socialnetwork.users.UserDao;
import io.redbee.socialnetwork.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    UserService userService;

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

       when(userService.getAllUsers()).thenReturn(users);

       this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
               .andExpect(status().isOk())
               .andDo(print());

    }

    @Test
    void testAgregarUser() throws Exception {

        User user = new User(1,"noe@gmail", "adejnafwf", "CREATED", LocalDateTime.of(2021,10, 12, 1,1), "noe", LocalDateTime.of(2021,10, 12, 1,1), "noe" );

        when(userService.createUser(user)).thenReturn(user);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(user);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/posts/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(jsonBody))
                .andExpect(status().is(201))
                .andDo(print());
    }
}