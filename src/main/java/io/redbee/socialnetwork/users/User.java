package io.redbee.socialnetwork.users;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class User {
    private final Integer id;
    private final String mail;
    private final String encryptedPassword;
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;

}
