package com.takeaway.dto;

public class OutputMessage {

    private String from;
    private String nextPlayer;
    private Integer number;
    private Integer divisionNumber;

    public OutputMessage() {
    }

    public OutputMessage(final String from, final Integer number) {

        this.from = from;
        this.number = number;
    }

    public void setNextPlayer(String nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    public Integer getNumber() {
        return number;
    }

    public String getFrom() {
        return from;
    }

    public Integer getDivisionNumber() {
        return divisionNumber;
    }

    public void setDivisionNumber(Integer divisionNumber) {
        this.divisionNumber = divisionNumber;
    }
}
