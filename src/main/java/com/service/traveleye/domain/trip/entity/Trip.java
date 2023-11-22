package com.service.traveleye.domain.trip.entity;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.destination.entity.Destination;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="destination_id")
    private Destination destination; //일단은 다중선택 아니라는 전제

    private String title; // 여정별명
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private State state; // 여행전 | 여행중 | 여행후
}
