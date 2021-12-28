package com.takeaway.service;

import com.takeaway.dto.Message;
import com.takeaway.dto.OutputMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.takeaway.config.SocketVariables.CHAT_SPECIFIC_USER;

@Service
public class GameService {
    private CopyOnWriteArrayList<String> waitingRoom = new CopyOnWriteArrayList();

    private final SimpMessagingTemplate template;

    public GameService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void handlePlayerInput(Message message) {
        OutputMessage outputMessage = new OutputMessage(message.getFrom(), message.getNumber());
        String nextPlayer = message.getTo();
        outputMessage.setNextPlayer(message.getFrom());
        template.convertAndSendToUser(nextPlayer, CHAT_SPECIFIC_USER, outputMessage);
    }

    public void handleNewGamer(String clientSessionId) {
        if (isThereAnyAvailablePlayerWaiting()) {
            waitingRoom.add(clientSessionId);
        }else{
            letFirstPlayerBegin(clientSessionId);
        }
    }

    private boolean isThereAnyAvailablePlayerWaiting() {
        return waitingRoom.size() == 0;
    }

    private void letFirstPlayerBegin(String secondPlayerId) {
        OutputMessage outputMessage = new OutputMessage();
        outputMessage.setNextPlayer(secondPlayerId);
        String firstPlayerId = waitingRoom.stream().findFirst().get();
        waitingRoom.clear();
        template.convertAndSendToUser(firstPlayerId, CHAT_SPECIFIC_USER, outputMessage);
    }
}
