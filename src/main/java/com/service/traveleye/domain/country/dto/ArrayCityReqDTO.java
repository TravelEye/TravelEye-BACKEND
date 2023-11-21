package com.service.traveleye.domain.country.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ArrayCityReqDTO {
    private List<String> cities;

    public ArrayCityReqDTO(List<String> cities) {
        this.cities = cities;
    }
}
