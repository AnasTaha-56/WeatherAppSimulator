package com.example.teststatic.MicroController;


import com.example.teststatic.Model.Humidity;
import com.example.teststatic.Model.Temperature;
import com.example.teststatic.Model.LocalMeasurement;

public class MicroController {
    private String _ipAddress;
    private boolean _status;
    private LocalMeasurement<Temperature> _temperatureRepository = new LocalMeasurement<>();
    private LocalMeasurement<Humidity> _humidityRepository = new LocalMeasurement<>();

    public MicroController(String ip) {
        _ipAddress = ip;
    }

    public String get_ipAddress() {
        return _ipAddress;
    }

    public void set_ipAddress(String ipAddress) {
        _ipAddress = ipAddress;
    }

    public boolean isStatus() {
        return _status;
    }

    public void setStatus(boolean status) {
        _status = status;
    }

    public LocalMeasurement<Temperature> getTemperatureRepository() {
        return _temperatureRepository;
    }

    public LocalMeasurement<Humidity> getHumidityRepository() {
        return _humidityRepository;
    }


}
