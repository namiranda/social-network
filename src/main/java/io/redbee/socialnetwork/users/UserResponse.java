package io.redbee.socialnetwork.users;

import lombok.Builder;
import lombok.Data;


@Data
@Builder(toBuilder = true)
public class UserResponse {
    Integer id;
    String mail;
    String status;

    public UserResponse(Integer id, String mail, String status) {
        this.id = id;
        this.mail = mail;
        this.status = status;
    }
}
