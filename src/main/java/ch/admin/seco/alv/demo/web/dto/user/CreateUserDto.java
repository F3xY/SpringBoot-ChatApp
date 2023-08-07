package ch.admin.seco.alv.demo.web.dto.user;

import java.time.Instant;

public class CreateUserDto {

    private final String nickname;
    private final Status status;
    private final int avatar;
    private final Instant updated;

    public CreateUserDto(final String nickname, final Status status, final int avatar, final Instant updated){
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

    public int getAvatar() {
        return avatar;
    }

    public Instant getUpdated() {
        return updated;
    }
}
