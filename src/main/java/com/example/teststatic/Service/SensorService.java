package com.example.teststatic.Service;

import com.example.teststatic.MicroController.MicroController;
import com.example.teststatic.Model.Humidity;
import com.example.teststatic.Model.Temperature;
import com.example.teststatic.Repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {


    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService( SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public SensorRepository getSensorRepository() {
        return sensorRepository;
    }

    public void addTemp(String ip, Temperature temp) {
        //sensorRepository.getSensor(ip).getTemperatureRepository().addMeasurement(temp);
        sensorRepository.addTemp(ip,temp);
    }
    public void addHumidity(String ip, Humidity humidity) {
        //sensorRepository.getSensor(ip).getTemperatureRepository().addMeasurement(temp);
        sensorRepository.addHumidity(ip, humidity);
    }



    public boolean exists(String ip) {
        return sensorRepository.exists(ip);
    }

    public void addSensor(MicroController microController) {
        sensorRepository.addSensor(microController);
    }


}
