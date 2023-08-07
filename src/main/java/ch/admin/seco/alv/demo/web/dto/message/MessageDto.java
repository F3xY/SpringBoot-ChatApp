package ch.admin.seco.alv.demo.web.dto.message;

import java.time.Instant;
import java.util.Date;

public class MessageDto {

    private final int id;
    private String message;
    private int userId;
    private Instant timestamp;

    public MessageDto(final int id, final String message, final int userId, Instant timestamp){
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}


