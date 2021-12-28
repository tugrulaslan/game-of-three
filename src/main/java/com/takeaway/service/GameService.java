package com.takeaway.service;

import com.takeaway.config.ProgramConfiguration;
import com.takeaway.dto.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.takeaway.config.SocketVariables.CHAT_SPECIFIC_USER_NAME;

@Service
public class GameService {
    private CopyOnWriteArrayList<String> waitingRoom = new CopyOnWriteArrayList();

    private final ProgramConfiguration programConfiguration;
    private final SimpMessagingTemplate template;

    public GameService(ProgramConfiguration programConfiguration,
                       SimpMessagingTemplate template) {
        this.programConfiguration = programConfiguration;
        this.template = template;
    }

    public void handlePlayerInput(Message playerMessage) {
        Message message = prepareNextPlayerMessage(playerMessage);
        template.convertAndSendToUser(playerMessage.getNextPlayer(), CHAT_SPECIFIC_USER_NAME, message);
    }

    public void handleNewGamer(String clientSessionId) {
        if (isThereAnyAvailablePlayerWaiting()) {
            waitingRoom.add(clientSessionId);
        }else{
            letFirstPlayerBegin(clientSessionId);
        }
    }

    private Message prepareNextPlayerMessage(Message playerMessage) {
        Message message = new Message();
        message.setFrom(playerMessage.getFrom());
        message.setNumber(playerMessage.getNumber());
        message.setNextPlayer(playerMessage.getFrom());
        message.setDivisionNumber(programConfiguration.getDivisionNumber());
        message.setWinningNumber(programConfiguration.getWinningNumber());
        return message;
    }

    private boolean isThereAnyAvailablePlayerWaiting() {
        return waitingRoom.size() == 0;
    }

    private void letFirstPlayerBegin(String secondPlayerId) {
        Message message = new Message();
        message.setNextPlayer(secondPlayerId);
        message.setDivisionNumber(programConfiguration.getDivisionNumber());
        message.setWinningNumber(programConfiguration.getWinningNumber());
        String firstPlayerId = waitingRoom.stream().findFirst().get();
        waitingRoom.clear();
        template.convertAndSendToUser(firstPlayerId, CHAT_SPECIFIC_USER_NAME, message);
    }
}
