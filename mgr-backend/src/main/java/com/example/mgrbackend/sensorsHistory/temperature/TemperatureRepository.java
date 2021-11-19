package com.example.mgrbackend.sensorsHistory.temperature;

import com.example.mgrbackend.sensor.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    @Query("SELECT s FROM Temperature s WHERE s.value = ?1")
    Optional<Temperature> findTemperature(String value);
}
