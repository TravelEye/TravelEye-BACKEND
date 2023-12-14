package com.service.traveleye.domain.trip.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripMemoAddReqDTO {

    private Long landmarkId;
    private String memo;
    private LocalDateTime date;
}
