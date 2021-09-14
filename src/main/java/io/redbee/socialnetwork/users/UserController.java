package io.redbee.socialnetwork.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
         return modelMapper.map(newUser, UserResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return modelMapper.map(user, UserResponse.class);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@RequestBody User user)  {
       User updatedUser = userService.updateUser(user);
        return modelMapper.map(updatedUser, UserResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse deleteUser(@PathVariable Integer id){
      User deletedUser = userService.deleteUser(id);
      return modelMapper.map(deletedUser, UserResponse.class);
    }


}
