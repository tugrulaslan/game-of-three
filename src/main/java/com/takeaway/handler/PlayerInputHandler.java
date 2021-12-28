package com.takeaway.handler;

import com.takeaway.dto.OutputMessage;
import com.takeaway.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import static com.takeaway.config.SocketVariables.CHAT_QUEUE_NAME;

@Controller
public class PlayerInputHandler {

    private final GameService gameService;

    public PlayerInputHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping(CHAT_QUEUE_NAME)
    public void handle(OutputMessage message) {
        gameService.handlePlayerInput(message);
    }
}
