package io.redbee.socialnetwork.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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

    public User findUserById(Integer id) throws Exception {
        Optional<User> userOp = userDao.getById(id);
        if(userOp.isPresent()){
            return userOp.get();
        }else {
            throw  new Exception("User not found??");
        }
    }

    public void updateUser(User user) {
       userDao.update(user);
    }
    public void deleteUser(Integer userId){
       // userDao.getById(userId).ifPresent(user->updateUser(user.withStatus("DELETED")));
    }


}
