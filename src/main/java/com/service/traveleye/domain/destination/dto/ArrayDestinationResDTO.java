package com.service.traveleye.domain.destination.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArrayDestinationResDTO {
    private Long id;
    private String continent;
    private String country;
    private String city;
}
