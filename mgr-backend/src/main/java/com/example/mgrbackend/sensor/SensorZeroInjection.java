package com.example.mgrbackend.sensor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class SensorZeroInjection {
        @Bean
        CommandLineRunner sensorCommandLineRunner (SensorRepository sensorRepository) {
            return args -> {
                Sensor sensor0 = new Sensor(1L, "616 H", "Mocked", "M");

                List<Sensor> initalList = new LinkedList<>();
                initalList.add(sensor0);

                sensorRepository.saveAll(initalList);
            };
        }
}
