package com.example.teststatic.Service;

import com.example.teststatic.DeviceWebSocketHandler;
import com.example.teststatic.Model.Humidity;
import com.example.teststatic.Model.Measurement;
import com.example.teststatic.Model.Temperature;
import com.example.teststatic.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Service
public class PayloadService {

    private PayloadReader _payloadReader;
    private SensorService _sensorService;
    private MessageUIService _messageUIService;
    private DeviceWebSocketHandler _deviceWebSocketHandler;

    @Autowired
    public PayloadService(PayloadReader payloadReader, SensorService sensorService,DeviceWebSocketHandler deviceWebSocketHandler  ,MessageUIService messageUIService) {
        _sensorService = sensorService;
        _payloadReader = payloadReader;
        _messageUIService = messageUIService;
        _deviceWebSocketHandler = deviceWebSocketHandler;
    }


    // rename getPayload
    public Measurement readPayload(String payload){
        PayloadReader payloadReader = new PayloadReader();
        Measurement measurement = payloadReader.readMessage(payload);

        return measurement;

    }

    public void sendMessage(String ip, Measurement measurement) throws IOException {

        System.out.println("Device IP :" + ip);

        String endpoint;
        String message;
        String messageEndpoint = "/messages";


        if (measurement.getType().equals("Temperature")) {
            switch (ip) {
                case "1" -> endpoint = "/temperatureRoomOne";
                case "2" -> endpoint = "/temperatureRoomTwo";
                case "3" -> endpoint = "/temperatureRoomThree";
                default -> endpoint = "/noWhereToGo";
            }
            message = "Room " + ip + " : " + _messageUIService.processTemperature((Temperature) measurement);
            System.out.println("TEMP found and added");
        } else if (measurement.getType().equals("Humidity")) {
            switch (ip) {
                case "1" -> endpoint = "/humidityRoomOne";
                case "2" -> endpoint = "/humidityRoomTwo";
                case "3" -> endpoint = "/humidityRoomThree";
                default -> endpoint = "/noWhereToGo";
            }
            message = "Room " + ip + " : " + _messageUIService.processHumidity((Humidity) measurement);
            System.out.println("HUMID found and added");
        } else {
            System.out.println("false reading");
            return;
        }

        // Send the message to the appropriate endpoint
        _deviceWebSocketHandler.broadcastMessage(endpoint, String.valueOf(measurement.getMeasurement()));
        _deviceWebSocketHandler.broadcastMessage(messageEndpoint, message);
    }

    public String convertEndianToString (byte[] bytes){

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        byte[] originalBytes = new byte[bytes.length];
        buffer.get(originalBytes);
        return new String(originalBytes);
    }


}
