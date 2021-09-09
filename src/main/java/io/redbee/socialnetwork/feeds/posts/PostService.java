package io.redbee.socialnetwork.feeds.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostDao postDao;

    public void crearPost(Post post){
        postDao.save(post);
    }

}
