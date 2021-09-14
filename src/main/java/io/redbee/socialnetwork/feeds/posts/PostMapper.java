package io.redbee.socialnetwork.feeds.posts;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMapper {

    public PostResponse postToResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .content(post.getContent())
                .status(post.getStatus())
                .build();
    }

    public Post requestToPost(PostRequest postRequest, Integer userId){
       return Post.builder()
               .userId(userId)
                .content(postRequest.getContent())
                .status("CREATED")
                .creationDate(LocalDateTime.now())
                .creationUser("System")
                .modificationDate(LocalDateTime.now())
                .modificationUser("System")
               .build();
    }
}
