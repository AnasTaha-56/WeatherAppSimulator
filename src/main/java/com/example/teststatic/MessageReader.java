package com.example.teststatic;

import com.example.teststatic.MicroController.MicroController;
import com.example.teststatic.Model.Measurement;
import com.example.teststatic.Service.PayloadService;
import com.example.teststatic.Service.SensorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MessageReader {


    private final SensorService sensorService;
    private final PayloadService payloadService;

    @Autowired
    public MessageReader(SensorService sensorService, PayloadService payloadService) {
        this.payloadService = payloadService;
        this.sensorService = sensorService;
    }
    @PostConstruct
    public void onStart() {
        System.out.println("Simulator Started");
    }
    @Scheduled(fixedRate = 2000)
    public void simulate() throws IOException {

        int ip = simulateIP();
        String deviceIp = String.valueOf(ip);

        byte[] simulatedPayload = simulateMess();
        String mess = payloadService.convertEndianToString(simulatedPayload);

        if (!sensorService.exists(deviceIp)) {
            sensorService.addSensor(new MicroController(deviceIp));
        }

        Measurement measurement = payloadService.readPayload(mess);

        payloadService.sendMessage(deviceIp, measurement);

    }

    public int simulateIP(){
        int randomInt = ThreadLocalRandom.current().nextInt(1, 4);
        System.out.println("Simulated IP is : " + randomInt);
        return randomInt;
    }

    public byte[] simulateMess(){
        int randomInt = ThreadLocalRandom.current().nextInt(0, 50);
        String simulatedPayload;
        byte[] bytes;
        if (randomInt < 25) {
            simulatedPayload = "t" + randomInt;
            //return "t" + randomInt;
        }else{
            simulatedPayload = "h" + randomInt;
            //return "h" + randomInt ;
        }

        bytes = simulatedPayload.getBytes();

        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(bytes);

        return buffer.array();
    }

}
