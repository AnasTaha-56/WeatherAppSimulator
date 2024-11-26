package com.example.teststatic.Repository;

import com.example.teststatic.MicroController.MicroController;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StatusRepository {

    private Map<Integer, String> sensors = new HashMap<Integer, String>();

    public int checkSensor(MicroController microController) {
        // TODO implement logic
        return 0;
    }

    public void addStatus(MicroController microController) {
        // TODO implement logic
    }


}
