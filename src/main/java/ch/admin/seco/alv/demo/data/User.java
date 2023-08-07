package ch.admin.seco.alv.demo.data;

import ch.admin.seco.alv.demo.web.dto.user.Status;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String nickname;
    private Status status;
    private int avatar;

    @UpdateTimestamp
    private Instant updated;

    public User(final String nickname, final Status status, final int avatar, final Instant updated){
        this.nickname = nickname;
        this.status = status;
        this.avatar = avatar;
        this.updated = updated;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAvatar() {
        return avatar;
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
