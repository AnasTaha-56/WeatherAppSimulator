package com.example.teststatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private DeviceWebSocketHandler deviceWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Register a single handler for both temperature and humidity of deviceOne
        registry.addHandler(deviceWebSocketHandler, "/temperatureRoomOne").setAllowedOrigins("*");
        registry.addHandler(deviceWebSocketHandler, "/humidityRoomOne").setAllowedOrigins("*");
        registry.addHandler(deviceWebSocketHandler, "/temperatureRoomTwo").setAllowedOrigins("*");
        registry.addHandler(deviceWebSocketHandler, "/humidityRoomTwo").setAllowedOrigins("*");
        registry.addHandler(deviceWebSocketHandler, "/temperatureRoomThree").setAllowedOrigins("*");
        registry.addHandler(deviceWebSocketHandler, "/humidityRoomThree").setAllowedOrigins("*");
        registry.addHandler(deviceWebSocketHandler, "/messages").setAllowedOrigins("*");
    }
}
