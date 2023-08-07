package ch.admin.seco.alv.demo.web.controller;

import ch.admin.seco.alv.demo.data.User;
import ch.admin.seco.alv.demo.service.UserService;
import ch.admin.seco.alv.demo.web.dto.user.UpdateUserDto;
import ch.admin.seco.alv.demo.web.socket.WebSocketController;
import ch.admin.seco.alv.demo.web.dto.user.CreateUserDto;
import ch.admin.seco.alv.demo.web.dto.user.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Users")
public class UserController {

    private final UserService userService;
    private final WebSocketController webSocketController;

    public UserController(UserService userService, WebSocketController webSocketController) {
        this.userService = userService;
        this.webSocketController = webSocketController;
    }

    //getAll
    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    //getUser
    @GetMapping("{id}")
    public UserDto getById(@PathVariable int id){
        return userService.getById(id);
    }

    //Create
    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto createUserDto){
        return userService.create(createUserDto);
    }

    //UpdateUser
    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable final int id, @RequestBody UpdateUserDto updateUserDto) {
        UserDto userDto = userService.updateUser(id, updateUserDto);
        webSocketController.sendPayload("user_updated", userDto);
        return userDto;
    }

    //DeleteUser
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        userService.deleteById(id);
        webSocketController.sendPayload("user_deleted", id);
    }

    @DeleteMapping()
    public void deleteAll() {
        userService.deleteAll();
    }
}
