package com.service.traveleye.domain.trip.entity;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="trip_test")
@NoArgsConstructor
@ToString
public class Trip  extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable = false)
    private Long tripId;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    private String country;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title; // 여정별명
    private State state; // 여행전 PENDING | 여행중 ACTIVE | 여행후 COMPLETED

    @Builder
    public Trip(Long tripId, Member member, String country, LocalDateTime startDate, LocalDateTime endDate, String title, State state) {
        this.tripId = tripId;
        this.member = member;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.state = state;
    }
}
