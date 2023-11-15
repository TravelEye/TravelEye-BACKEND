package com.service.traveleye.domain.location.service;

import com.service.traveleye.domain.location.dto.LocationRecommendReqDTO;
import com.service.traveleye.domain.location.entity.Location;
import com.service.traveleye.domain.location.util.GeometryUtil;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @Override
    public DataResDTO<?> getRecommendList(String email, LocationRecommendReqDTO locationRecommendReqDTO) {
        // Location 자료형으로 변수를 선언하여 해당 요청받은 x,y 값으로 북동쪽과 남서쪽의 위치를 계산
        Location northEast = GeometryUtil.calculate(locationRecommendReqDTO.getX(), locationRecommendReqDTO.getY(), 2.0, Direction.NORTHEAST.getBearing());
        Location southWest = GeometryUtil.calculate(locationRecommendReqDTO.getX(), locationRecommendReqDTO.getY(), 2.0, Direction.SOUTHWEST.getBearing());
        String pointFormat = String.format(
                "'LINESTRING(%f %f, %f %f)'",
                northEast.getLatitude(), northEast.getLongitude(), southWest.getLatitude(), southWest.getLongitude()
        );
        return null;
    }
}
