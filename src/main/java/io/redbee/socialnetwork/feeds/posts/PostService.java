package io.redbee.socialnetwork.feeds.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostDao postDao;

    public List<Post> getByUserId(Integer userId){
       return postDao.getByUserId(userId);
    }

    public Post getPost(Integer postId){
       return postDao.getById(postId).orElseThrow();
    }

    public Post crearPost(Post post){
        return postDao.save(post).orElseThrow();
    }

    public Post updatePost(Post post, Integer postId){
        Post toUpdate = postDao.getById(postId).orElseThrow();
        Post updated = Post.builder()
                .id(toUpdate.getId())
                .userId(toUpdate.getUserId())
                .content(post.getContent())
                .status(toUpdate.getStatus())
                .creationDate(toUpdate.getCreationDate())
                .creationUser(toUpdate.getCreationUser())
                .modificationDate(LocalDateTime.now())
                .modificationUser("System")
                .build();

        return postDao.update(updated);

    }

    public Post deletePost( Integer postId){
        Post toDelete = postDao.getById(postId).orElseThrow();
        return postDao.update(toDelete.withStatus("DELETED"));
    }

}
