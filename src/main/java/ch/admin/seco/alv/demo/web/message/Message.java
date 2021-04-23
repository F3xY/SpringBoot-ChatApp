package ch.admin.seco.alv.demo.web.message;

public class Message {

    private final int id;

    private String message;

    private final int user_id;

    public Message(String message, int user_id, int id) {
        this.message = message;
        this.user_id = user_id;
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getUser_id() {
        return  user_id;
    }

    public int getId() {
        return id;
    }
}


