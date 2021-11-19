package com.example.mgrbackend.sensor;

import javax.persistence.*;

@Entity
@Table
public class Sensor {

    @Id
    @SequenceGenerator(
            name = "sensor_sequence",
            sequenceName = "sensor_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sensor_sequence"
    )

    private Long id;
    private String value;
    private String name;
    private String unit;

    public Sensor() {};

    public Sensor(Long id, String value, String name, String unit) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.unit = unit;
    }

    public Sensor(String value, String name, String unit) {
        this.value = value;
        this.name = name;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
