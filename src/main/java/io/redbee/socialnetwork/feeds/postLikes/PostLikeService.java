package io.redbee.socialnetwork.feeds.postLikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    @Autowired
    PostLikeDao postLikeDao;

    public void crearPostLike(PostLike postLike){
        postLikeDao.save(postLike);
    }
}
