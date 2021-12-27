package com.takeaway.dto;

public class Message {
    private String from;
    private String to;
    private Integer text;

//    TODO: rename text to number and change to integer!

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public Integer getText() {
        return text;
    }
    public void setText(Integer text) {
        this.text = text;
    }
}
