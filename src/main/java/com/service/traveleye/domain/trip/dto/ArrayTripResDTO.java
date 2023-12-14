package com.service.traveleye.domain.trip.dto;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.entity.TripMemo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArrayTripResDTO {
    private Long tripId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private State state;
    private String country;
    private List<TripMemo> memos;
    @Builder
    public ArrayTripResDTO(Long tripId,LocalDateTime startDate, LocalDateTime endDate, String country, String title, State state, List<TripMemo> memos){
        this.tripId=tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.country = country;
        this.title = title;
        this.state = state;
        this.memos = memos;
    }

}
