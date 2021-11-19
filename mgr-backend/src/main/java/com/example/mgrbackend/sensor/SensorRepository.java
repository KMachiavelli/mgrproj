package com.example.mgrbackend.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>{
        @Query("SELECT s FROM Sensor s WHERE s.name = ?1")
        Optional<Sensor> findSensorByName(String name);

//        @Query("SELECT * FROM Sensor s")
//        Optional<Sensor> findAllSensors();

        @Query(value = "SELECT * FROM Sensor s",
                nativeQuery = true)
        List<Sensor> findAllSensors();
}
