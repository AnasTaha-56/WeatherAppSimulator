package com.example.teststatic;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DeviceWebSocketHandler extends TextWebSocketHandler {

    // Map to track sessions for each endpoint
    private final Map<String, List<WebSocketSession>> sessionsMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String path = session.getUri().getPath(); // Get the endpoint path (e.g., "/deviceOne/temperature")
        sessionsMap.computeIfAbsent(path, k -> new ArrayList<>()).add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String path = session.getUri().getPath();
        List<WebSocketSession> sessions = sessionsMap.get(path);
        if (sessions != null) {
            sessions.remove(session);
        }
    }

    // Method to broadcast messages to all sessions for a specific endpoint
    public void broadcastMessage(String endpoint, String message) throws IOException {
        List<WebSocketSession> sessions = sessionsMap.get(endpoint);
        if (sessions != null) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
    }
}

