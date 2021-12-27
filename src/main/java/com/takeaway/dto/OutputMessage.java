package com.takeaway.dto;

public class OutputMessage {

    private String from;
    private String nextPlayer;
    private String text;
    private String time;
    private boolean init;
    private String clientId;

    public OutputMessage(String clientId, boolean init) {
        this.clientId = clientId;
        this.init = init;
    }

    public OutputMessage(final String from, final String text, final String time) {

        this.from = from;
        this.text = text;
        this.time = time;
    }

    public void setNextPlayer(String nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }

    public boolean isInit() {
        return init;
    }

    public String getClientId() {
        return clientId;
    }
}
