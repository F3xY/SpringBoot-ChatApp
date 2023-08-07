package ch.admin.seco.alv.demo.service;

import ch.admin.seco.alv.demo.data.User;
import ch.admin.seco.alv.demo.data.UserRepository;
import ch.admin.seco.alv.demo.web.dto.user.CreateUserDto;
import ch.admin.seco.alv.demo.web.dto.user.UpdateUserDto;
import ch.admin.seco.alv.demo.web.dto.user.UserDto;
import ch.admin.seco.alv.demo.web.dto.user.Status;
import ch.admin.seco.alv.demo.web.socket.WebSocketController;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WebSocketController webSocketController;

    public UserService(UserRepository userRepository, WebSocketController webSocketController) {
        this.userRepository = userRepository;
        this.webSocketController = webSocketController;
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto getById(final int id) {
        return convertToDto(userRepository.getOne(id));
    }

    public UserDto create(CreateUserDto createUserDto) {
        User user = doesUserExist(createUserDto.getNickname());
        if (user == null){
            user = userRepository.save(new User(
                    createUserDto.getNickname(),
                    createUserDto.getStatus(),
                    createUserDto.getAvatar(),
                    createUserDto.getUpdated()
            ));
            System.out.println("Create user with ID " + user.getId());
            webSocketController.sendPayload("user_added", convertToDto(user));
        } else {
            System.out.println("User exists already " + user.getId());
        }
        return convertToDto(user);
    }

    private User doesUserExist(String username){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getNickname(), username)){
               return user;
            }
        }
        return null;
    }

    public UserDto updateUser(final int id, UpdateUserDto updateUserDto) {
        final User user = userRepository.getOne(id);
        user.setNickname(user.getNickname());
        user.setStatus(updateUserDto.getStatus());
        user.setAvatar(user.getAvatar());
        System.out.println("Update user with ID " + user.getId());
        return convertToDto(userRepository.save(user));
    }

    public void deleteById(final int id){
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void updateStatus(){
        List<User> users = userRepository.findAll();
        Instant away = Instant.now().minus(30, ChronoUnit.MINUTES);
        Instant offline = away.minus(30, ChronoUnit.MINUTES);

        for (User user: users) {
            Instant lastUpdate = user.getUpdated();
            if (lastUpdate.isAfter(offline)){
                user.setStatus(Status.online);
                userRepository.save(user);
            } else if(lastUpdate.isAfter(away)){
                user.setStatus(Status.away);
                userRepository.save(user);
            }
        }
    }

    private UserDto convertToDto(User user){
        return new UserDto(
                user.getId(),
                user.getNickname(),
                user.getStatus(),
                user.getAvatar(),
                user.getUpdated()
        );
    }
}
