package ch.admin.seco.alv.demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageEntity {
    @Id
    @GeneratedValue
    private int id;

    private String messageText;

    private int user_id;

    public MessageEntity(String messageText, int user_id) {
        this.messageText = messageText;
        this.user_id = user_id;

    }

    public MessageEntity() {

    }

    public String getMessageText() {
        return messageText;
    }

    public int getUser_id() {
        return  user_id;
    }

    public int getId() {
        return id;
    }
}
