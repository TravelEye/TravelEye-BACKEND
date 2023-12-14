package com.service.traveleye.domain.landmark.service;

import com.service.traveleye.domain.landmark.dto.LandmarkListResDTO;
import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.landmark.repository.LandmarkRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
private final LandmarkRepository landmarkRepository;



    @Override
    public DataResDTO<?> getLandmarkList() {
       return DataResDTO.builder()
               .message("랜드마크 전체 조회 성공")
               .data(landmarkRepository.findAll())
               .build();
    }

    @Override
    public DataResDTO<?> getLandmarkByPlace(String place) {
        return DataResDTO.builder()
                .message("랜드마크 검색 성공")
                .data(landmarkRepository.findLandmarkByPlaceStartingWith(place))
                .build();
    }

//    @Override
//    public DataResDTO<?> getRecommendList(String email, LocationRecommendReqDTO locationRecommendReqDTO) {
//        // Location 자료형으로 변수를 선언하여 해당 요청받은 x,y 값으로 북동쪽과 남서쪽의 위치를 계산
//        Location northEast = GeometryUtil.calculate(locationRecommendReqDTO.getX(), locationRecommendReqDTO.getY(), 2.0, Direction.NORTHEAST.getBearing());
//        Location southWest = GeometryUtil.calculate(locationRecommendReqDTO.getX(), locationRecommendReqDTO.getY(), 2.0, Direction.SOUTHWEST.getBearing());
//        String pointFormat = String.format(
//                "'LINESTRING(%f %f, %f %f)'",
//                northEast.getLatitude(), northEast.getLongitude(), southWest.getLatitude(), southWest.getLongitude()
//        );
//        return null;
//    }
}
