package com.example.pub.models;

public class ErrorMessage {
    private String text;

    public ErrorMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
