package com.service.traveleye.domain.trip.entity;

import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="tripmemo_test") /* 여행지-메모 테이블 */
@NoArgsConstructor
@ToString
public class TripMemo extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    private String memo; /* 여행지에 대한 메모 작성 */

    private LocalDateTime date;

    @Builder
    public TripMemo(Long id, Landmark landmark, Trip trip, String memo, LocalDateTime date) {
        this.id = id;
        this.landmark = landmark;
        this.trip = trip;
        this.memo = memo;
        this.date = date;
    }
}
