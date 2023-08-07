package ch.admin.seco.alv.demo.web.dto.user;

import java.time.Instant;

public class UserDto {

    /*
    Create the user. Modifiers are private, due to access of getter and setters.
     */

    private final int id;
    private String nickname;
    private Status status;
    private int avatar;
    private Instant updated;

    public UserDto(final int id, String nickname, Status status, int avatar, Instant updated){
        this.id = id;
        this.nickname = nickname;
        this.status = status;
        this.avatar = avatar;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public Status getStatus() {
        return status;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

}
