package com.service.traveleye.domain.landmark.entity;

import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
@Getter
@Table(name="landmark_test")
@NoArgsConstructor
@ToString
public class Landmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long landmarkId;
    private String landmarkName;
    private Double latitude;
    private Double longitude;

//    private Point point;
}
