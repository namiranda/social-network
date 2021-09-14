package io.redbee.socialnetwork.users;

import io.redbee.socialnetwork.users.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.get();
    }

    public User createUser(User user) {
        return this.userDao.save(user).orElseThrow();
    }

    public User findUserById(Integer id) {
        return  userDao.getById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    public User updateUser(User user, Integer id) {
        User toUpdate = userDao.getById(id).orElseThrow();
        User updated = User.builder()
                .id(id)
                .mail(user.getMail())
                .encryptedPassword(toUpdate.getEncryptedPassword())
                .status(toUpdate.getStatus())
                .creationDate(toUpdate.getCreationDate())
                .creationUser(toUpdate.getCreationUser())
                .modificationDate(LocalDateTime.now())
                .modificationUser("System")
                .build();

      return userDao.update(updated);
    }
    public User deleteUser(Integer userId){
        return userDao.getById(userId)
                .map(user -> userDao.update(user.withStatus(Status.DELETED.name())))
                .orElseThrow();
    }


}
