package io.redbee.socialnetwork.feeds.posts;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PostDao {
    private final NamedParameterJdbcTemplate template;

    public PostDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDao.class);

    private static final String getQuery = "SELECT " +
            "id, " +
            "user_id, " +
            "content, " +
            "status, " +
            "creation_date, " +
            "creation_user, " +
            "modification_date, " +
            "modification_user " +
            "FROM posts";

    private static final String insertQuery = "" +
            "INSERT INTO posts (user_id, content, status, creation_date, creation_user, modification_date, modification_user) " +
            "VALUES (:user_id, :content, :status, :creation_date, :creation_user, :modification_date, :modification_user) ";

    private static final String updateQuery = "" +
            "UPDATE posts " +
            "SET content = :content, " +
            "    status             = :status, " +
            "    modification_date  = :modification_date, " +
            "    modification_user  = :modification_user " +
            "WHERE id = :id";

    public void save(Post post) {
        try {
            template.update(insertQuery, postToParamSource(post));
            LOGGER.info("save: post from user {} saved", post.getUserId());
        } catch (Exception e) {
            LOGGER.info("save: error {} saving post from user {}", e.getMessage(), post.getUserId());
        }
    }

    public void update(Post post) {
        try {
            template.update(updateQuery, postToParamSource(post));
            LOGGER.info("update: post {} updated", post.getId());
        } catch (Exception e) {
            LOGGER.info("update: error {} updating post {}", e.getMessage(), post.getId());
        }
    }

    public List<Post> get() {
        try {
            List<Post> result = template.query(getQuery, new PostRowMapper());
            LOGGER.info("get: posts found: {}", result);
            return result;
        } catch (DataAccessException e) {
            LOGGER.info("get: error {} searching posts", e.getMessage());
            throw new RepositoryException();
        }
    }

    public Optional<Post> getById(Integer id) {
        try {
            Optional<Post> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE id = :id",
                            Map.of("id", id),
                            new PostRowMapper()
                    )
            );
            LOGGER.info("getById: post found: {}", result);
            return result;
        } catch (ResourceAccessException e) {
            LOGGER.info("getById: post with id {} not found", id);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getById: error {} searching post with id: {}", e.getMessage(), id);
            throw new RepositoryException();
        }
    }

    public Optional<Post> getByUserId(Integer userId) {
        try {
            Optional<Post> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE user_id = :user_id",
                            Map.of("user_id", userId),
                            new PostRowMapper()
                    )
            );
            LOGGER.info("getByUserId: post found: {}", result);
            return result;
        } catch (ResourceAccessException e) {
            LOGGER.info("getByUserId: post with user id {} not found", userId);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getByUserId: error {} searching post with user id: {}", e.getMessage(), userId);
            throw new RepositoryException();
        }
    }

    private MapSqlParameterSource postToParamSource(Post post) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", post.getId());
        params.addValue("user_id", post.getUserId());
        params.addValue("content", post.getContent());
        params.addValue("status", post.getStatus());
        params.addValue("creation_date", post.getCreationDate());
        params.addValue("creation_user", post.getCreationUser());
        params.addValue("modification_date", post.getModificationDate());
        params.addValue("modification_user", post.getModificationUser());

        return params;
    }
}
