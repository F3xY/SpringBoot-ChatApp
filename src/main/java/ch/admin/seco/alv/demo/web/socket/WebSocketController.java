package ch.admin.seco.alv.demo.web.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class WebSocketController extends TextWebSocketHandler
{
    private final List<WebSocketSession> activeSessions = new CopyOnWriteArrayList<>();

    private final ObjectMapper objectMapper;

    public WebSocketController(final ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    private static final class Message<T>
    {
        private final String action;

        private final T data;

        public static <T> Message<T> of(final String action, final T data)
        {
            return new Message<>(action, data);
        }

        public Message(final String action, final T data)
        {
            this.action = action;
            this.data = data;
        }

        public String getAction()
        {
            return action;
        }

        public T getData()
        {
            return data;
        }
    }

    public <T> void sendPayload(final String action, final T payload)
    {
        try
        {
            final String res = objectMapper.writeValueAsString(Message.of(action, payload));
            for (final WebSocketSession activeSession : activeSessions) {
                activeSession.sendMessage(new TextMessage(res));
            }
        }
        catch(final IOException exception)
        {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session)
    {
        activeSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status)
    {
        activeSessions.remove(session);
    }
}

