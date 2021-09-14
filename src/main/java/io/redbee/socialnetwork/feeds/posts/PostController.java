package io.redbee.socialnetwork.feeds.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users/{userId}/posts")
public class PostController {

    PostService postService;
    PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest postRequest, @PathVariable Integer userId) {
       Post newPost = postService.crearPost(postMapper.requestToPost(postRequest, userId));
        System.out.println(postMapper.postToResponse(newPost).toString());
       return postMapper.postToResponse(newPost);
    }

}
