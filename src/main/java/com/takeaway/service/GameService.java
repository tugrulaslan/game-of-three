package com.takeaway.service;

import com.takeaway.config.ProgramConfiguration;
import com.takeaway.dto.Message;
import com.takeaway.dto.OutputMessage;
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

    public void handlePlayerInput(Message message) {
        OutputMessage outputMessage = new OutputMessage(message.getFrom(), message.getNumber());
        String nextPlayer = message.getTo();
        outputMessage.setNextPlayer(message.getFrom());
        outputMessage.setDivisionNumber(programConfiguration.getDivisionNumber());
        outputMessage.setWinningNumber(programConfiguration.getWinningNumber());
        template.convertAndSendToUser(nextPlayer, CHAT_SPECIFIC_USER_NAME, outputMessage);
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
        outputMessage.setDivisionNumber(programConfiguration.getDivisionNumber());
        outputMessage.setWinningNumber(programConfiguration.getWinningNumber());
        String firstPlayerId = waitingRoom.stream().findFirst().get();
        waitingRoom.clear();
        template.convertAndSendToUser(firstPlayerId, CHAT_SPECIFIC_USER_NAME, outputMessage);
    }
}
