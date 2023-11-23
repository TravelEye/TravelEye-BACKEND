package com.service.traveleye.domain.trip.controller;

import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.repository.TripRepository;
import com.service.traveleye.domain.trip.service.TripService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;
    private final TripRepository tripRepository;
    @PostMapping("/new")
    public boolean addTrip(@RequestBody TripAddReqDTO tripAddReqDTO){
        String email = "user1@gmail.com";
        return tripService.addTrip(email,tripAddReqDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTrip(@PathVariable Long id){
        return tripService.deleteTripById(id);
    }

    @PutMapping("")
    public boolean updateTrip( @RequestBody TripUpdateReqDTO tripUpdateReqDTO){
        String email =  "user1@gmail.com";
        tripService.updateTrip(email,tripUpdateReqDTO);
        return true;
    }
}
