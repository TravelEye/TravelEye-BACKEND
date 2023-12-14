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

    @GetMapping("")
    public List<ArrayTripResDTO>  getTrips( @AuthenticationPrincipal MemberDetails memberDetails){
        String email = extractMemberEmail(memberDetails);
        return tripService.getByMemberId(email);
    }
    @PostMapping("/new")
    public boolean addTrip(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody TripAddReqDTO tripAddReqDTO){
        String email = extractMemberEmail(memberDetails);
        return tripService.addTrip(email,tripAddReqDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTrip(@AuthenticationPrincipal MemberDetails memberDetails,@PathVariable Long id){
        return tripService.deleteTripById(id);
    }

    @PutMapping("")
    public boolean updateTrip( @AuthenticationPrincipal MemberDetails memberDetails,@RequestBody TripUpdateReqDTO tripUpdateReqDTO){
        String email = extractMemberEmail(memberDetails);
        tripService.updateTrip(email,tripUpdateReqDTO);
        return true;
    }

    private String extractMemberEmail(MemberDetails memberDetails) {
        return memberDetails.getMember().getEmail();
    }
}
