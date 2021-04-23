package ch.admin.seco.alv.demo.service;

import ch.admin.seco.alv.demo.data.UserEntity;
import ch.admin.seco.alv.demo.data.UserRepository;
import ch.admin.seco.alv.demo.web.user.CreateUser;
import ch.admin.seco.alv.demo.web.user.UpdateUser;
import ch.admin.seco.alv.demo.web.user.User;
import ch.admin.seco.alv.demo.web.user.UserStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return maptoUser(userRepository.findAll());
    }

    public User getById(final int id) {
        return mapToUser(userRepository.getOne(id));
    }

    public User create(CreateUser createUser) {
        final UserEntity userEntity = userRepository.save(new UserEntity(
                createUser.getNickname(),
                UserStatus.online
        ));
        return mapToUser(userEntity);
    }

    public User update(final int id, UpdateUser updateUser) {
        final UserEntity userEntity = userRepository.getOne(id);
        if (updateUser.getNickname() != null) {
            userEntity.setNickname(updateUser.getNickname());
        }
        if (updateUser.getStatus() != null) {
            switch (updateUser.getStatus()){
                case "online":
                    userEntity.setStatus(UserStatus.online);
                case "offline":
                    userEntity.setStatus(UserStatus.offline);
                case "away":
                    userEntity.setStatus(UserStatus.away);
            }
        }
        return mapToUser(userRepository.save(userEntity));
    }

    public User deleteById(final int id){
        User delUser = getById(id);
        userRepository.deleteById(id);
        return delUser;
    }


    private static List<User> maptoUser(final List<UserEntity> userEntities) {
        final List<User> users = new ArrayList<>();
        for (final UserEntity userEntity : userEntities) {
            users.add(mapToUser(userEntity));
        }
        return users;
    }

    private static User mapToUser(final UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getNickname(),
                userEntity.getStatus()
        );
    }
}
