package com.service.traveleye.domain.trip.dto;

import com.service.traveleye.domain.config.entity.State;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TripUpdateReqDTO {
    private Long tripId;
    private String country;
    private String city;
    private String title; // 여정별명
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private State state; // PENDING

    private List<TripMemoAddReqDTO> memos;


}
