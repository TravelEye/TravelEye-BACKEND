package com.service.traveleye.domain.landmark.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LandmarkListResDTO {
    private Long landmarkId;
    private String landmarkName;
    private Double latitude; //위도
    private Double longitude; //경도

    @Builder
    public LandmarkListResDTO (Long landmarkId, String landmarkName, Double latitude, Double longitude) {
        this.landmarkId = landmarkId;
        this.landmarkName = landmarkName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
