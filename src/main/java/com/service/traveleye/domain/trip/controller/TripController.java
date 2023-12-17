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

    @GetMapping("/all")
    public DataResDTO<?>  getTrips( @AuthenticationPrincipal MemberDetails memberDetails){
        Long id = memberDetails.getMember().getId();
        return tripService.getByMemberId(id);
    }
    @PostMapping("/new")
    public DataResDTO<?> addTrip(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody TripAddReqDTO tripAddReqDTO){
        Long id = memberDetails.getMember().getId();
        return tripService.addTrip(id,tripAddReqDTO);
    }

    @DeleteMapping("/{id}")
    public DataResDTO<?> deleteTrip(@AuthenticationPrincipal MemberDetails memberDetails,@PathVariable Long id){
        return tripService.deleteTripById(id);
    }

    @PutMapping("")
    public DataResDTO<?> updateTrip( @AuthenticationPrincipal MemberDetails memberDetails,@RequestBody TripUpdateReqDTO tripUpdateReqDTO){
        Long id = memberDetails.getMember().getId();
       return tripService.updateTrip(id,tripUpdateReqDTO);

    }


}
