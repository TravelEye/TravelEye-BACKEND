package com.service.traveleye.domain.trip.service;

import com.service.traveleye.domain.trip.dto.ArrayTripResDTO;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.global.dto.DataResDTO;

import java.util.List;

public interface TripService {
    DataResDTO<?> addTrip(Long id,TripAddReqDTO tripAddReqDTO);
    DataResDTO<?> deleteTripById(Long id);
    DataResDTO<?> updateTrip(Long id,TripUpdateReqDTO tripUpdateReqDTO);

    DataResDTO<?> getByMemberId(Long id);
}
