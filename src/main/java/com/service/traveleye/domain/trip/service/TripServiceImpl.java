package com.service.traveleye.domain.trip.service;

import com.service.traveleye.domain.destination.entity.Destination;
import com.service.traveleye.domain.destination.repository.DestinationRepository;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.repository.TripRepository;
import com.service.traveleye.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripServiceImpl implements TripService{
private final TripRepository tripRepository;
private final MemberRepository memberRepository;
private final DestinationRepository destinationRepository;
    @Override
    public Boolean addTrip(String email, TripAddReqDTO tripAddReqDTO) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Member not found"));
        Destination destination = destinationRepository.findById(tripAddReqDTO.getDestinationId())
                .orElseThrow(() -> new NotFoundException("Destination not found"));
        Trip trip = new Trip();
        trip.setMember(member);
        trip.setDestination(destination);
        trip.setTitle(tripAddReqDTO.getTitle());
        trip.setStartDate(tripAddReqDTO.getStartDate());
        trip.setEndDate(tripAddReqDTO.getEndDate());
        trip.setState(tripAddReqDTO.getState());


        System.out.println(trip.toString());
        tripRepository.save(trip);

        return true;
    }
}
