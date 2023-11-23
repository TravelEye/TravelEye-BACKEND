package com.service.traveleye.domain.trip.dto;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.destination.entity.Destination;
import com.service.traveleye.domain.destination.entity.Plan;
import com.service.traveleye.domain.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TripAddReqDTO {
    private Long destinationId;
    private String title; // 여정별명
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private State state; // 여행전 | 여행중 | 여행후
    private List<Plan> plans; //Day1 런던아이 맛집가기
}
