package ch.admin.seco.alv.demo.web.controller;

import ch.admin.seco.alv.demo.service.MessageService;
import ch.admin.seco.alv.demo.web.socket.WebSocketController;
import ch.admin.seco.alv.demo.web.message.CreateMessage;
import ch.admin.seco.alv.demo.web.message.Message;
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
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping("{id}")
    public Message getById(@RequestParam(name = "id") final int id) {
        return messageService.getById(id);
    }

    @PostMapping
    public Message create(@RequestBody CreateMessage createMessage){
        Message message = messageService.create(createMessage);
        webSocketController.sendPayload("message_added",message);
        return  message;
    }

    @DeleteMapping("{id}")
    public void delete(@RequestParam(name = "id") final int id){
        messageService.deleteById(id);
    }
}
