package ch.admin.seco.alv.demo.web.dto.message;

import java.time.Instant;

public class CreateMessageDto {

    private String message;
    private int userId;
    private Instant timestamp;

    CreateMessageDto(final String message, final int userId){
        this.message = message;
        this.userId = userId;
        this.timestamp = Instant.now();
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