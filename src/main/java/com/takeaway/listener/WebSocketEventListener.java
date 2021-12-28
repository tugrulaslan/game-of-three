package com.takeaway.listener;

import com.takeaway.service.GameService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketEventListener {

    private final GameService gameService;

    public WebSocketEventListener(GameService gameService) {
        this.gameService = gameService;
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        GenericMessage message = (GenericMessage) event.getMessage();
        gameService.handleNewGamer((String) message.getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER));
    }
}
