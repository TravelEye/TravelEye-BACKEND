package com.service.traveleye.domain.destination.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArrayCityResDTO {
    private List<CityResDTO> cities;

}
