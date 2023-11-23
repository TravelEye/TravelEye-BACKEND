package com.service.traveleye.domain.trip.service;

import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;

public interface TripService {
    boolean addTrip(String email, TripAddReqDTO tripAddReqDTO);
    boolean deleteTripById(Long id);
    boolean updateTrip(String email, TripUpdateReqDTO tripUpdateReqDTO);
}
