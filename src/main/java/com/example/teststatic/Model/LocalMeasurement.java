package com.example.teststatic.Model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LocalMeasurement<T> {
    private final ArrayList<T> _measurements = new ArrayList<>();
    private final int MAX_SIZE = 4;
    private boolean _added = false;

    public boolean addMeasurement(T measurement) {
        _added = _measurements.add(measurement);
        if (_measurements.size() == MAX_SIZE){
            //TODO SENDEN TO DB
            _measurements.clear();
        }
        return _added;
    }


}
