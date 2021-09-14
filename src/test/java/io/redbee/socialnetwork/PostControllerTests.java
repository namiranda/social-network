package io.redbee.socialnetwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.socialnetwork.feeds.posts.Post;
import io.redbee.socialnetwork.feeds.posts.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class PostControllerTests {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    PostService postService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postService).build();
    }

    @Test
    void testAgregarPost() throws Exception {

        Post post = new Post(15,9, "Hola","CREATED", LocalDateTime.of(2021,10, 12, 1,1), "noe", LocalDateTime.of(2021,10, 12, 1,1), "noe" );

       // Mockito.when(postService.crearPost(post)).thenReturn(post);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(post);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/posts/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(new Post(15,9, "Hola","CREATED", LocalDateTime.of(2021,10, 12, 1,1), "noe", LocalDateTime.of(2021,10, 12, 1,1), "noe" ))))
                .andExpect(status().is(201))
                .andDo(print());

    }

}
