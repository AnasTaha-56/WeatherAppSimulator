package com.example.teststatic.Model;

import java.time.LocalDateTime;

public class Temperature implements Measurement {
    private float _temperature;
    private LocalDateTime _timeStamp = LocalDateTime.now();

    public float get_temperature() {
        return _temperature;
    }

    public void setFloat(float _temperature) {
        this._temperature = _temperature;
    }

    public LocalDateTime get_timeStamp() {
        return _timeStamp;
    }

    public void set_timeStamp(LocalDateTime _timeStamp) {
        this._timeStamp = _timeStamp;
    }

    @Override
    public String getType() {
        return "Temperature";
    }
    @Override
    public float getMeasurement(){
        return _temperature;
    }
}
