package com.service.traveleye.domain.trip.dto;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.trip.entity.Trip;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArrayTripResDTO {
    private Long tripId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String plans;
    private String title;
    private State state;

    @Builder
    public ArrayTripResDTO(Long tripId,LocalDateTime startDate, LocalDateTime endDate, String plans, String title, State state){
        this.tripId=tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plans = plans;
        this.title = title;
        this.state = state;
    }

}
