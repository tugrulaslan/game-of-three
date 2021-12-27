package com.takeaway.service;

import com.takeaway.dto.Message;
import com.takeaway.dto.OutputMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import static com.takeaway.config.SocketVariables.SECURED_CHAT_SPECIFIC_USER;

@Service
public class GameService {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    private final SimpMessagingTemplate template;

    private static final String FIRST_PLAYER_SESSION_ID_KEY = "FIRST_PLAYER_SESSION_ID";
    private static final String SECOND_PLAYER_SESSION_ID_KEY = "SECOND_PLAYER_PLAYER_SESSION_ID";
    private static final String NUMBER_KEY = "NUMBER";

    public GameService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void handlePlayerInput(Message message) {
        if (gameIsNotInitiated()) {
            initiateTheGame(message);
        } else {
            //game continues
            Integer number = (Integer) map.get(NUMBER_KEY);
            number += Integer.parseInt(message.getText());
            number /= 3;
            map.put(NUMBER_KEY, number);
            final String time = new SimpleDateFormat("HH:mm").format(new Date());
            OutputMessage outputMessage = new OutputMessage(message.getFrom(), String.valueOf(number), time);
            String nextPlayer = message.getTo();
            outputMessage.setNextPlayer(message.getFrom());
            template.convertAndSendToUser(nextPlayer, SECURED_CHAT_SPECIFIC_USER, outputMessage);
        }
    }

    public void handleSubscribedEvent(String clientSessionId) {
        if (firstPlayerIsNotJoinedTheGame()) {
            initializeFirstPlayer(clientSessionId);
            return;
        }
        if (secondPlayerIsNotJoinedTheGame()) {
            initializeSecondPlayer(clientSessionId);
            letFirstPlayerBegin();
        }
        //todo: reject other clients
    }

    private boolean gameIsNotInitiated() {
        return map.get(NUMBER_KEY) == null;
    }

    private void initiateTheGame(Message message) {
        Integer number = Integer.valueOf(message.getText());
        map.put(NUMBER_KEY, number);
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage outputMessage = new OutputMessage("server", String.valueOf(number), time);
        outputMessage.setNextPlayer(retrievePlayerName(FIRST_PLAYER_SESSION_ID_KEY));
        template.convertAndSendToUser(retrievePlayerName(SECOND_PLAYER_SESSION_ID_KEY), SECURED_CHAT_SPECIFIC_USER, outputMessage);
    }

    private void letFirstPlayerBegin() {
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage outputMessage = new OutputMessage("server", "Fire the game", time);
        outputMessage.setNextPlayer(retrievePlayerName(SECOND_PLAYER_SESSION_ID_KEY));
        template.convertAndSendToUser(retrievePlayerName(FIRST_PLAYER_SESSION_ID_KEY), SECURED_CHAT_SPECIFIC_USER, outputMessage);
    }

    private String retrievePlayerName(String userKey) {
        return (String) map.get(userKey);
    }

    private boolean firstPlayerIsNotJoinedTheGame() {
        return map.get(FIRST_PLAYER_SESSION_ID_KEY) == null;
    }

    private void initializeFirstPlayer(String clientSessionId) {
        map.put(FIRST_PLAYER_SESSION_ID_KEY, clientSessionId);
    }

    private boolean secondPlayerIsNotJoinedTheGame() {
        return map.get(FIRST_PLAYER_SESSION_ID_KEY) != null
                && map.get(SECOND_PLAYER_SESSION_ID_KEY) == null;
    }

    private void initializeSecondPlayer(String clientSessionId) {
        map.put(SECOND_PLAYER_SESSION_ID_KEY, clientSessionId);
    }
}
