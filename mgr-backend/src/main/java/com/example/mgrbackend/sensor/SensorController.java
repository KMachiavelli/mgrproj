package com.example.mgrbackend.sensor;

import com.example.mgrbackend.sensorsHistory.temperature.Temperature;
import com.example.mgrbackend.sensorsHistory.temperature.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class SensorController {
    private SensorService sensorService;

    private TemperatureService temperatureService;

    @Autowired
    SensorController(SensorService sensorService, TemperatureService temperatureService) {
        this.sensorService = sensorService;
        this.temperatureService = temperatureService;
    }

    @ResponseBody
    @GetMapping(value = "/status_sensor")
    HashMap<String, String> getStatus() {
        HashMap<String, String> status = new HashMap<>();
        status.put("sensor-section", "I'm alive");
        return status;
    }

    @ResponseBody
    @PostMapping(value = "/add_sensor")
    public HashMap<String, String> registerSensor(@RequestBody Sensor sensor) {
        HashMap<String, String> response = new HashMap<>();
        try {
            sensorService.addSensor(sensor);
            response.put("response", "sensor-loaded: ".concat(sensor.getName()));
            response.put("isOK", "OK");
            // System.out.println(sensor);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("response", "error: ".concat(e.getMessage()));
            return response;
        }
    }

    @ResponseBody
    @PostMapping(value = "/add_sensors")
    public HashMap<String, String> registerSensor(@RequestBody List<Sensor> sensors) {
        HashMap<String, String> response = new HashMap<>();
        try {
            sensorService.addSensor(sensors);
            response.put("response", "sensors-loaded: ".concat(sensors.toString()));
            response.put("isOK", "OK");

            temperatureService.addTemperature(sensors.get(1).getValue());
            // System.out.println(sensors);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("response", "error: ".concat(e.getMessage()));
            return response;
        }
    }

    @ResponseBody
    @GetMapping( value = "/get_sensors")
    public List<Sensor> getSensors() {
        List<Sensor> returnSensors = sensorService.getSensors();
        // System.out.println(returnSensors);
        return returnSensors;
    }

//    @PostMapping(value = "/login_app_user")
//    public HashMap<String, String> loginAppUser(@RequestBody AppUser appUser) {
//        HashMap<String, String> response = new HashMap<>();

//        response.put("response", appUserService.checkLogin(appUser));
//        return response;
//    }
}
