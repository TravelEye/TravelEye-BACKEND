package com.service.traveleye.domain.trip.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripUpdateReqDTO {
    private Long id;
    private Long destinationId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
