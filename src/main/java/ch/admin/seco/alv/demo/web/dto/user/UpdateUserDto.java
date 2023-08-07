package ch.admin.seco.alv.demo.web.dto.user;

import java.time.Instant;

public class UpdateUserDto {

    // Declared final, user already exists
    private final String nickname;
    private final Status status;
    private final Integer avatar;
    private Instant updated;


    public UpdateUserDto(final String nickname, final Status status, final Integer avatar, Instant updated){
        this.nickname = nickname;
        this.status = status;
        this.avatar = avatar;
        this.updated = updated;

    }

    public String getNickname() {
        return nickname;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getAvatar() {
        return avatar;
    }
}
