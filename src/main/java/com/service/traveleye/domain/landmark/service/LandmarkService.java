package com.service.traveleye.domain.landmark.service;


import com.service.traveleye.domain.landmark.dto.LandmarkListResDTO;
import com.service.traveleye.global.dto.DataResDTO;

import java.util.List;

public interface LandmarkService {
//    DataResDTO<?> getRecommendList(String email,LocationRecommendReqDTO locationRecommendReqDTO);
List<LandmarkListResDTO> getLandmarkList();
}
