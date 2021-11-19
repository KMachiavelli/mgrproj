package com.example.mgrbackend.sensorsHistory.temperature;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class Temperature {

    @Id
    @SequenceGenerator(
            name = "temperature_sequence",
            sequenceName = "temperature_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "temperature_sequence"
    )

    private Long id;
    private String value;
    private Date date;
    private Double time;

    public Temperature() {};

    public Temperature(Long id, String value, Date date, Double time) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.time = time;
    };

    public Temperature(String value, Date date, Double time) {
        this.value = value;
        this.date = date;
        this.time = time;
    };

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Double getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}

