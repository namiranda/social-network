package io.redbee.socialnetwork.feeds.postLikes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/{userId}/posts/{postId}/likes")
public class PostLikeController {

    PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostLike> crearPostLike(@PathVariable Integer postId, @RequestHeader Integer userId){
        PostLike newLike = new PostLike(postId, userId);
        postLikeService.crearPostLike(newLike);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLike);
    }

}
