package com.service.traveleye.domain.trip.repository;

import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.entity.TripMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripMemoRepository extends JpaRepository<TripMemo,Long> {

    List<TripMemo> findByTripTripId(Long tripId);
    TripMemo findByTripAndLandmark(Trip trip, Landmark landmark);
    List<TripMemo> deleteTripMemosByTrip(Trip trip);
  }
