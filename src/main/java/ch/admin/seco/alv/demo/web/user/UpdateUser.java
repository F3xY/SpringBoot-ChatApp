package ch.admin.seco.alv.demo.web.user;

public class UpdateUser {
    private final String nickname;
    private final String status;

    public UpdateUser(String nickname, String status) {
        this.nickname = nickname;
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public String getStatus() {
        return status;
    }
}
