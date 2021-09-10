package io.redbee.socialnetwork.feeds.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(value = "/posts", consumes = "application/json")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        postService.crearPost(post);
        return ResponseEntity.status(201).body(post);
    }

}
