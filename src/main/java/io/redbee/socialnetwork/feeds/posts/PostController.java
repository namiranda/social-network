package io.redbee.socialnetwork.feeds.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/users/{userId}/posts")
public class PostController {

    PostService postService;
    PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPost(@PathVariable Integer userId){
        List<Post> posts = postService.getByUserId(userId);
        return posts.stream()
                .map(post ->  postMapper.postToResponse(post))
                .collect(Collectors.toList());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostById(@PathVariable Integer postId){
        return postMapper.postToResponse(postService.getPost(postId));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest postRequest, @PathVariable Integer userId) {
       Post newPost = postService.crearPost(postMapper.requestToPost(postRequest, userId));
       return postMapper.postToResponse(newPost);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@RequestBody PostRequest postRequest, @PathVariable Integer userId, @PathVariable Integer postId){
        Post updated = postService.updatePost(postMapper.requestToPost(postRequest, userId) ,postId);
        return postMapper.postToResponse(updated);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse deletePost(@PathVariable Integer postId){
        Post deleted = postService.deletePost(postId);
        return postMapper.postToResponse(deleted);
    }

}
