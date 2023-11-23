package com.service.traveleye.domain.destination.entity;

import com.service.traveleye.domain.landmark.entity.Landmark;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class Plan {
    private Long landmarkId;
    private String memo;
}
