package ch.admin.seco.alv.demo.data;

import ch.admin.seco.alv.demo.web.user.UserStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;

    private String nickname;

    private UserStatus status;

    protected UserEntity() {}

    public UserEntity(String nickname, UserStatus status) {
        this.nickname = nickname;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
