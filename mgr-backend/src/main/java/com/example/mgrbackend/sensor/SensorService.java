package com.example.mgrbackend.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private SensorRepository sensorRepository;

    @Autowired
    SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> getSensors() {
        return sensorRepository.findAll();
    }

    public void addSensor(Sensor sensor) {
         // System.out.println(sensor);
        try {
            sensorRepository.save(sensor);
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void  addSensor(List<Sensor> sensors) {
        // System.out.println(sensors);
        try {
            sensorRepository.saveAll(sensors);
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSensor(Sensor sensor) {
        // System.out.println(sensor);
        // System.out.println(sensor.getName());
        try {
            Optional<Sensor> appUserOptional = sensorRepository.findSensorByName(sensor.getName());
            if (appUserOptional.isPresent()) {
                System.out.println("OK");
                return "OK PASSES";
            } else return "WRONG PASSES";
        } catch (Exception e) {
            System.out.println("ERROR: ".concat(e.getMessage()));
            return "ERROR: ".concat(e.getMessage());
        }
    }
}
