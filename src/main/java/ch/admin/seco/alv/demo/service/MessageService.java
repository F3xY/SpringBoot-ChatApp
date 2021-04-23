package ch.admin.seco.alv.demo.service;

import ch.admin.seco.alv.demo.data.MessageEntity;
import ch.admin.seco.alv.demo.data.MessageRepository;
import ch.admin.seco.alv.demo.web.message.CreateMessage;
import ch.admin.seco.alv.demo.web.message.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAll() {
        return mapToMessage(messageRepository.findAll());
    }

    public Message getById(final int id) {
        return mapTomessage(messageRepository.getOne(id));
    }

    public Message create(CreateMessage createMessage) {
        final MessageEntity messageEntity = messageRepository.save(new MessageEntity(
                createMessage.getMessageText(),
                createMessage.getUser_id()
        ));
        return mapTomessage(messageEntity);
    }

    public void deleteById(int id) {
        List<Message> messages = getAll();
        for (Message message : messages){
            if (message.getUser_id() == id){
                messageRepository.deleteById(message.getId());
            }
        }
    }

    private static List<Message> mapToMessage(final List<MessageEntity> messageEntities) {
        final List<Message> messages = new ArrayList<>();
        for (final MessageEntity messageEntity : messageEntities) {
            messages.add(mapTomessage(messageEntity));
        }
        return messages;
    }

    private static Message mapTomessage(final MessageEntity messageEntity) {
        return new Message(
                messageEntity.getMessageText(),
                messageEntity.getUser_id(),
                messageEntity.getId()
        );
    }
}
