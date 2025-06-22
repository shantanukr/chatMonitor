package com.thedevguy.chatMonitor.model;

public class ChatResponse {
    private String sender;
    private String message;
    private double riskScore;

    public ChatResponse(String sender, String message, double riskScore) {
        this.sender = sender;
        this.message = message;
        this.riskScore = riskScore;
    }

    public String getSender() { return sender; }
    public String getMessage() { return message; }
    public double getRiskScore() { return riskScore; }
}
