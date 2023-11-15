package com.service.traveleye.domain.location.service;

import com.service.traveleye.domain.location.dto.LocationRecommendReqDTO;
import com.service.traveleye.global.dto.DataResDTO;

public interface LocationService {
    DataResDTO<?> getRecommendList(String email,LocationRecommendReqDTO locationRecommendReqDTO);
}
