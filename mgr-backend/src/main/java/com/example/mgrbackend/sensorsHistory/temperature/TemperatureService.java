package com.example.mgrbackend.sensorsHistory.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TemperatureService {
    TemperatureRepository temperatureRepository;

    @Autowired
    TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public void addTemperature(Temperature temperature) {
        try {
            temperatureRepository.save(temperature);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTemperature(String value){
        Date date = new Date();
        Double time = (double) date.getTime();

        Temperature temperature = new Temperature(value, date, time);
        try {
            temperatureRepository.save(temperature);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };
}
