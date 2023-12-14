package com.service.traveleye.domain.trip.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TripUpdateReqDTO {
    private Long tripId;
    private String country;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<TripMemoUpdateReqDTO> memos;
}
