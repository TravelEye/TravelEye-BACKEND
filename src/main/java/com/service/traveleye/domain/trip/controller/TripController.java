package com.service.traveleye.domain.trip.controller;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.trip.dto.ArrayTripResDTO;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.repository.TripRepository;
import com.service.traveleye.domain.trip.service.TripService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;
    private final TripRepository tripRepository;

    @GetMapping("")
    public List<ArrayTripResDTO>  getTrips( @AuthenticationPrincipal MemberDetails memberDetails){
        System.out.println(memberDetails.getUsername());
        return tripService.getByMemberId(memberDetails.getMember().getEmail());
    }
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
