package com.takeaway.dto;

public class Message {

    private String from;
    private String nextPlayer;
    private Integer number;
    private Integer divisionNumber;
    private Integer winningNumber;

    public Message() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(String nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDivisionNumber() {
        return divisionNumber;
    }

    public void setDivisionNumber(Integer divisionNumber) {
        this.divisionNumber = divisionNumber;
    }

    public Integer getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(Integer winningNumber) {
        this.winningNumber = winningNumber;
    }
}
