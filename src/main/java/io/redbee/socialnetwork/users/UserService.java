package io.redbee.socialnetwork.users;

import io.redbee.socialnetwork.users.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


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

    public User updateUser(User user) {
       userDao.update(user);
       return userDao.getById(user.getId()).get();
    }
    public void deleteUser(Integer userId){
        userDao.getById(userId).ifPresent(user->updateUser(user.withStatus(Status.DELETED.name())));
    }


}
