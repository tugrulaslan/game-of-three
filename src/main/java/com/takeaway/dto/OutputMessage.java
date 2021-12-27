package com.takeaway.dto;

public class OutputMessage {

    private String from;
    private String nextPlayer;
    private Integer text;

    public OutputMessage() {
    }

    public OutputMessage(final String from, final Integer text) {

        this.from = from;
        this.text = text;
    }

    public void setNextPlayer(String nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    public Integer getText() {
        return text;
    }

    public String getFrom() {
        return from;
    }
}
