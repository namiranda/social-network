package io.redbee.socialnetwork.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(userMapper::userToRespose)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.requestToUser(userRequest);
        User newUser = userService.createUser(user);
         return userMapper.userToRespose(newUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return userMapper.userToRespose(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest)  {
       User updatedUser = userService.updateUser(userMapper.requestToUser(userRequest), id);
        return userMapper.userToRespose(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse deleteUser(@PathVariable Integer id){
      User deletedUser = userService.deleteUser(id);
      return userMapper.userToRespose(deletedUser);
    }


}
