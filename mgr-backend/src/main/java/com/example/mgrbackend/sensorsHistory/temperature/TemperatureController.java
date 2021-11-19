package com.example.mgrbackend.sensorsHistory.temperature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class TemperatureController {
    TemperatureService temperatureService;

    TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @ResponseBody
    @PostMapping(value = "/add_temperature")
    public HashMap<String, String> registerTemperature(@RequestBody Temperature temperature) {
        temperatureService.addTemperature(temperature);
        System.out.println("Registered".concat(temperature.toString()));
        HashMap<String, String> response = new HashMap<>();
        response.put("result", "added");
        return response;
    }
}
