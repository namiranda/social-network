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

    @PostMapping(value = "/users", consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
         return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Integer id) throws Exception {
        return userService.findUserById(id);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user)  {
        userService.updateUser(user);
        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }


}
