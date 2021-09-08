package io.redbee.socialnetwork.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users/new", consumes = "application/json")
    public void createUser(@RequestBody User user) {
         userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Integer id) throws Exception {
        return userService.findUserById(id);
    }


    @DeleteMapping("/users/delete")
    public boolean deleteUser(int id){
        return false;
    }


}
