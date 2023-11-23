package com.service.traveleye.domain.trip.service;

import com.service.traveleye.domain.trip.dto.ArrayTripResDTO;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;

import java.util.List;

public interface TripService {
    boolean addTrip(String email, TripAddReqDTO tripAddReqDTO);
    boolean deleteTripById(Long id);
    boolean updateTrip(String email, TripUpdateReqDTO tripUpdateReqDTO);

    List<ArrayTripResDTO> getByMemberId(String email);
}
