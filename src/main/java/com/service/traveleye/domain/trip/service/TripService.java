package com.service.traveleye.domain.trip.service;

import com.service.traveleye.domain.trip.dto.TripAddReqDTO;

public interface TripService {
    Boolean addTrip(String email, TripAddReqDTO tripAddReqDTO);
}
