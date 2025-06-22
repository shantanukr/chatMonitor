package com.thedevguy.chatMonitor.controller;

import com.thedevguy.chatMonitor.model.ChatMessage;
import com.thedevguy.chatMonitor.model.ChatResponse;
import com.thedevguy.chatMonitor.service.RiskAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RiskAnalyzer riskAnalyzer;
    private final Map<String, String> sessions = new ConcurrentHashMap<>();

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, RiskAnalyzer riskAnalyzer) {
        this.messagingTemplate = messagingTemplate;
        this.riskAnalyzer = riskAnalyzer;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatResponse handleMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        sessions.put(sessionId, message.getSender());
        System.out.println("Received message from: " + message.getSender());
        double riskScore = riskAnalyzer.analyze(message.getContent());

        if (riskScore > 0.8) {
            System.out.println("[ALERT] High-risk message from " + message.getSender() + ": " + message.getContent());
        }

        return new ChatResponse(message.getSender(), message.getContent(), riskScore);
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        sessions.remove(sessionId);
    }
}