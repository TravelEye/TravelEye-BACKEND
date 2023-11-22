package com.service.traveleye.domain.trip.controller;

import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.service.TripService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;
    @PostMapping("/new")
    public boolean addTrip(@RequestBody TripAddReqDTO tripAddReqDTO){
        String email = "user1@gmail.com";
        return tripService.addTrip(email,tripAddReqDTO);
    }
}
