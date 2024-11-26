package com.example.teststatic.Service;

import com.example.teststatic.Model.Humidity;
import com.example.teststatic.Model.Measurement;
import com.example.teststatic.Model.Temperature;
import org.springframework.stereotype.Service;

@Service
public class PayloadReader {

    private Measurement _message;

    public Measurement readMessage(String message){
        String[] myPayload = message.split("");
        StringBuilder data = new StringBuilder();
        if(myPayload.length > 0) {
            for (String s : myPayload) {
                switch (s) {
                    case "t":
                        _message = new Temperature();
                        break;
                    case "h":
                        _message = new Humidity();
                        break;
                    default:
                        data.append(s);
                        break;

                }
            }
            float _measurement = Float.parseFloat(data.toString());
            _message.setFloat(_measurement);

            return _message;
        } else {
            return null;
        }
    }

}
