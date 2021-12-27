package com.takeaway.listener;

import com.takeaway.service.GameService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketEventListener {

    private final GameService gameService;

    public WebSocketEventListener(GameService gameService) {
        this.gameService = gameService;
    }

    @EventListener
    public void handleSessionSubscribeEvent(AbstractSubProtocolEvent event) {
        GenericMessage message = (GenericMessage) event.getMessage();
        String simpDestination = (String) message.getHeaders().get("simpDestination");
        if (event instanceof SessionConnectEvent) {
            System.out.println("1. user registration");
        } else if (event instanceof SessionConnectedEvent) {
            System.out.println("2. second phase after session connect event -> in this phase we shall send user some payload");
        } else if (event instanceof SessionSubscribeEvent) {
            gameService.handleSubscribedEvent((String) message.getHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER));
            System.out.println("3. third phase user sends session subscribe event");

        }


//        if (simpDestination.startsWith("/topic/group/1")) {
//            // do stuff
//        }
    }

//    TODO: kill he game on disconnect game service SessionDisconnectEvent
}
