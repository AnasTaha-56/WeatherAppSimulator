package com.example.teststatic.Model;

import java.time.LocalDateTime;

public class Humidity implements Measurement {

    private float _humidity;
    private LocalDateTime _timeStamp = LocalDateTime.now();

    public float get_humidity() {
        return _humidity;
    }

    public void setFloat(float _humidity) {
        this._humidity = _humidity;
    }

    public LocalDateTime get_timeStamp() {
        return _timeStamp;
    }

    public void set_timeStamp(LocalDateTime _timeStamp) {
        this._timeStamp = _timeStamp;
    }

    @Override
    public String getType() {
        //return this.getType().toString();
        return "Humidity";
    }

    @Override
    public float getMeasurement(){
        return _humidity;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "_humidity=" + _humidity +
                ", _timeStamp=" + _timeStamp +
                '}';
    }

}
