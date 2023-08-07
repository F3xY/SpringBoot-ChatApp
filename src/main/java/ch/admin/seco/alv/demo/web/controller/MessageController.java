package ch.admin.seco.alv.demo.web.controller;

import ch.admin.seco.alv.demo.service.MessageService;
import ch.admin.seco.alv.demo.web.socket.WebSocketController;
import ch.admin.seco.alv.demo.web.dto.message.CreateMessageDto;
import ch.admin.seco.alv.demo.web.dto.message.MessageDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Messages")
public class MessageController {
    private final MessageService messageService;
    private final WebSocketController webSocketController;

    public MessageController(MessageService messageService, WebSocketController webSocketController) {
        this.messageService = messageService;
        this.webSocketController = webSocketController;
    }

    @GetMapping
    public List<MessageDto> getAll() {
        return messageService.getAll();
    }

    @GetMapping("{id}")
    public MessageDto getById(@PathVariable int id) {
        return messageService.getById(id);
    }

    @PostMapping
    public MessageDto create(@RequestBody CreateMessageDto createMessageDto){
        MessageDto messageDto = messageService.create(createMessageDto);
        webSocketController.sendPayload("message_added",messageDto);
        return messageDto;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        messageService.deleteById(id);
        System.out.println("Message deleted");
        webSocketController.sendPayload("message_deleted",id);
    }

    @DeleteMapping
    public void deleteAllMessages() {
        messageService.deleteAll();
    }
}
