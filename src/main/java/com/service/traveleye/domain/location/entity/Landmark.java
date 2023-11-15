package com.service.traveleye.domain.location.entity;

import com.service.traveleye.global.BaseEntity;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
public class Landmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int landmarkId;
    private Double latitude;

    private Double longitude;

    private Point point;
}
