package ch.admin.seco.alv.demo.service;

import ch.admin.seco.alv.demo.data.Message;
import ch.admin.seco.alv.demo.data.MessageRepository;
import ch.admin.seco.alv.demo.web.dto.message.CreateMessageDto;
import ch.admin.seco.alv.demo.web.dto.message.MessageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public List<MessageDto> getAll() {
        return messageRepository.findAll().stream().map(message -> convertToDto(message)).collect(Collectors.toList());

    }

    public MessageDto getById(final int id) {
        return convertToDto(messageRepository.getOne(id));
    }

    public MessageDto create(CreateMessageDto createMessageDto) {
        Message message = messageRepository.save(new Message(
                createMessageDto.getMessage(),
                createMessageDto.getUserId(),
                createMessageDto.getTimestamp()
        ));
        return convertToDto(message);
    }

    public void deleteById(final int id){
        messageRepository.deleteById(id);
    }
    public void deleteAll() {
        messageRepository.deleteAll();
    }


    private MessageDto convertToDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getMessage(),
                message.getUserId(),
                message.getTimestamp()
        );
    }
}
