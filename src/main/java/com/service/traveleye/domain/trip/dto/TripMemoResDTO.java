package com.service.traveleye.domain.trip.dto;

import com.service.traveleye.domain.landmark.entity.Landmark;

import java.time.LocalDateTime;

public class TripMemoResDTO {
    private Long tripMemoId;
    private Landmark landmark;
    private String memo;
    private LocalDateTime date;
}
