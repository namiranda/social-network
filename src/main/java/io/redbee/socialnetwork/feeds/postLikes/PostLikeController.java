package io.redbee.socialnetwork.feeds.postLikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostLikeController {

    @Autowired
    PostLikeService postLikeService;

    @PostMapping("/postlike/new")
    void crearPostLike(@RequestBody PostLike postLike){
        postLikeService.crearPostLike(postLike);
    }

}
