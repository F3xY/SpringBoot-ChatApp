package ch.admin.seco.alv.demo.web.user;

public class User {
    private final int id;
    private String nickname;
    private UserStatus status;

    public User(int id, String nickname, UserStatus status) {
        this.id = id;
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
