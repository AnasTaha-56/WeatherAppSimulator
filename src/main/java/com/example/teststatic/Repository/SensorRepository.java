package com.example.teststatic.Repository;

import com.example.teststatic.MicroController.MicroController;
import com.example.teststatic.Model.Humidity;
import com.example.teststatic.Model.Measurement;
import com.example.teststatic.Model.Temperature;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SensorRepository {
    private final Map<String, MicroController> _sensors = new HashMap<String, MicroController>();

    public boolean exists(String id) {
        return _sensors.containsKey(id);
    }

    public void addSensor(MicroController microController) {
        _sensors.put(microController.get_ipAddress(), microController);
    }
    public MicroController getSensor(String id) {
        return _sensors.get(id);
    }

    public void addTemp(String ip, Temperature temp) {
        _sensors.get(ip).getTemperatureRepository().addMeasurement(temp);
    }
    public void addHumidity(String ip, Humidity humidity) {
        _sensors.get(ip).getHumidityRepository().addMeasurement( humidity);
    }
}
