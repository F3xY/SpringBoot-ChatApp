package ch.admin.seco.alv.demo.web.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer
{
    private final WebSocketController webSocketController;

    public WebSocketConfig(final WebSocketController webSocketController)
    {
        this.webSocketController = webSocketController;
    }

    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry webSocketHandlerRegistry)
    {
        webSocketHandlerRegistry.addHandler(webSocketController, "/ws").setAllowedOrigins("*");
    }
}
