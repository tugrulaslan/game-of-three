package com.takeaway.dto;

public class OutputMessage {

    private String from;
    private String nextPlayer;
    private String text;

    public OutputMessage() {
    }

    public OutputMessage(final String from, final String text) {

        this.from = from;
        this.text = text;
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

    public String getFrom() {
        return from;
    }
}
