package com.service.traveleye.domain.landmark.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
public class Location {
    private Double latitude;
    private Double longitude;

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}


