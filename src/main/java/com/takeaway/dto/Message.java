package com.takeaway.dto;

public class Message {
    private String from;
    private String to;
    private String text;

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
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
