package io.redbee.socialnetwork.feeds.posts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PostResponse {
    private Integer id;
    private Integer userId;
    private String content;
    private String status;
}
