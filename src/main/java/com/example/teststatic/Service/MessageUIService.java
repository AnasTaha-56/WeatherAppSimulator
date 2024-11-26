package com.example.teststatic.Service;

import com.example.teststatic.Model.Humidity;
import com.example.teststatic.Model.Measurement;
import com.example.teststatic.Model.Temperature;
import org.springframework.stereotype.Service;

@Service
public class MessageUIService {



    public String processTemperature(Temperature temperature) {

        String message;

        float temp = temperature.get_temperature();

        if (temp < 0) {
            message = "Temperature is very low. Consider wearing warm clothing or heating the space.";
        } else if (temp >= 0 && temp < 15) {
            message = "Temperature is low. A sweater or light jacket is recommended.";
        } else if (temp >= 15 && temp <= 25) {
            message = "Temperature is comfortable. No additional action needed.";
        } else if (temp > 25 && temp <= 30) {
            message = "Temperature is slightly warm. Stay hydrated and dress in light clothing if needed.";
        } else if (temp > 30 && temp <= 35) {
            message = "Temperature is warm. Keep cool by drinking water and avoiding strenuous activities.";
        } else if (temp > 35) {
            message = "Temperature is very high. It's recommended to stay indoors, use air conditioning, and stay hydrated.";
        } else {
            message = "Temperature reading is out of the expected range. Please check the sensor.";
        }

        return message;

    }


    public String processHumidity(Humidity measurement) {

        String message;

        float humidity = measurement.get_humidity();


        if (humidity > 20 && humidity < 40) {
            message = "Humidity is low. Consider using a humidifier if the air feels too dry.";
        } else if (humidity >= 40 && humidity <= 60) {
            message = "Humidity is at a comfortable level.";
        } else if (humidity > 60 && humidity <= 80) {
            message = "Humidity is a bit high. Ventilate the area or use a dehumidifier if it feels uncomfortable.";
        } else if (humidity > 80) {
            message = "High humidity detected. It is recommended to use a dehumidifier or air conditioner.";
        } else {
            message = "Humidity reading is out of the expected range. Please check the sensor.";
        }

        return message;
    }

}
