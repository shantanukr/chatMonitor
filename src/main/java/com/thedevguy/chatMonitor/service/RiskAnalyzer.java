package com.thedevguy.chatMonitor.service;

import org.springframework.stereotype.Service;

@Service
public class RiskAnalyzer {
    public double analyze(String content) {
        return content.toLowerCase().contains("suicide") ? 0.95 : 0.2;
    }
}
