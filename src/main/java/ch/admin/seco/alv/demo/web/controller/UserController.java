package ch.admin.seco.alv.demo.web.controller;

import ch.admin.seco.alv.demo.service.UserService;
import ch.admin.seco.alv.demo.web.socket.WebSocketController;
import ch.admin.seco.alv.demo.web.user.CreateUser;
import ch.admin.seco.alv.demo.web.user.UpdateUser;
import ch.admin.seco.alv.demo.web.user.User;
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
    public List<User> getAll(){
        return userService.getAll();
    }

    //getUser
    @GetMapping("{id}")
    public User getById(@RequestParam(name = "id") final int id){
        return userService.getById(id);
    }

    //Create
    @PostMapping
    public User createUser(@RequestBody CreateUser createUser){
        User user = userService.create(createUser);
        webSocketController.sendPayload("user_added", user);
        return user;
    }

    //UpdateUser
    @PutMapping("{id}")
    public User update(@RequestParam(name = "id") final int id, @RequestBody UpdateUser updateUser){
        User user = userService.update(id, updateUser);
        webSocketController.sendPayload("user_updated", user);
        return user;
    }

    //DeleteUser
    @DeleteMapping("{id}")
    public User delete(@RequestParam(name = "id") final int id){
        User user = userService.deleteById(id);
        webSocketController.sendPayload("user_deleted",user);
        return user;
    }
}
