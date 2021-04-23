package ch.admin.seco.alv.demo.web.message;

public class CreateMessage {
    private String messageText;

    private final int user_id;

    public CreateMessage(String messageText, int user_id) {
        this.messageText = messageText;
        this.user_id = user_id;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getUser_id() {
        return  user_id;
    }
}
