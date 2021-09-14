package io.redbee.socialnetwork.users;

import io.redbee.socialnetwork.users.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class UserMapper {

    public UserResponse userToRespose(User user){
        return new UserResponse(user.getId(), user.getMail(), user.getStatus());
    }

    public User requestToUser(UserRequest userRequest){
        return User.builder()
                .mail(userRequest.getMail())
                .encryptedPassword(userRequest.password) // te debo la encriptacion
                .status(Status.CREATED.name())
                .creationDate(LocalDateTime.now())
                .creationUser("System")
                .modificationDate(LocalDateTime.now())
                .modificationUser("System")
                .build();
    }
}
