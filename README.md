# 💬 Chat Monitor - Spring Boot WebSocket Application

This project is a **real-time chat monitoring service** built using **Java Spring Boot** and **WebSocket (STOMP over SockJS)**. It supports sending and receiving chat messages in real time, and includes a simple **risk analyzer** that detects sensitive content (e.g., related to mental health or self-harm) and calculates a **risk score**.

---

## 🚀 Features

- 🔌 Real-time messaging using WebSocket + STOMP
- 🧠 Simple risk analysis engine (`RiskAnalyzer`) to score messages
- 🗨️ Broadcasts messages to all subscribed users
- 🧪 Compatible with Postman or frontend testing clients
- 🌐 CORS support enabled for frontend use

---

## 🧱 Project Structure

```bash
src/main/java/com/example/chatmonitor/
│
├── ChatMonitorApplication.java         # Main application entry
│
├── config/
│   └── WebSocketConfig.java            # WebSocket + STOMP config
│
├── controller/
│   └── ChatController.java             # Handles message routing
│
├── service/
│   └── RiskAnalyzer.java               # Dummy analyzer for message risk scoring
│
└── model/
    ├── ChatMessage.java                # Incoming message model
    └── ChatResponse.java               # Outgoing response model with risk score
 ```

## Prerequisites
- Java 17+
- Maven or Gradle
- IntelliJ IDEA (Community Edition works fine)
- Postman (for testing)

## Clone & Run
- git clone https://github.com/your-username/chat-monitor.git
- cd chat-monitor
- ./mvnw spring-boot:run

## WebSocket Testing
```
WebSocket URL: ws://localhost:8080/chat/websocket
Send Destination: /app/chat
Subscribe Destination: /topic/messages
```
## Postman STOMP Test
- Open Postman
- Create New → WebSocket Request
- Connect to: ws://localhost:8080/chat/websocket
#### Subscribe:
```
SUBSCRIBE
id:sub-0
destination:/topic/messages

^@
Send:
```
#### SEND
```
destination:/app/chat
content-type:application/json

{
  "sender": "Alice",
  "content": "I feel very sad"
}
^@
```
You should receive a broadcast message:

{
  "sender": "Alice",
  "message": "I feel very sad",
  "riskScore": 0.95
}

## Optional Frontend

A simple HTML test client under ```src/main/resources/static/user_interface.html``` can be added for browser-based interaction.