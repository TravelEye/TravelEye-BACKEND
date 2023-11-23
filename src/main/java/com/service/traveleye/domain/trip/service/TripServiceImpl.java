package com.service.traveleye.domain.trip.service;

import com.service.traveleye.domain.destination.entity.Destination;
import com.service.traveleye.domain.destination.repository.DestinationRepository;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.trip.dto.ArrayTripResDTO;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.repository.TripRepository;
import com.service.traveleye.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripServiceImpl implements TripService{
private final TripRepository tripRepository;
private final MemberRepository memberRepository;
private final DestinationRepository destinationRepository;
    @Override
    public boolean addTrip(String email, TripAddReqDTO tripAddReqDTO) {
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


        tripRepository.save(trip);

        return true;
    }

    @Override
    public boolean deleteTripById(Long id) {
        try {
            tripRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }

    @Override
    public boolean updateTrip(String email, TripUpdateReqDTO tripUpdateReqDTO) {
        Trip trip = tripRepository.getById(tripUpdateReqDTO.getId());
        Destination destination = destinationRepository.findById(tripUpdateReqDTO.getDestinationId()).get();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Member not found"));

        trip.setId(tripUpdateReqDTO.getId());
        trip.setDestination(destination);
        trip.setState(trip.getState());
        trip.setStartDate(tripUpdateReqDTO.getStartDate());
        trip.setEndDate(tripUpdateReqDTO.getEndDate());
        trip.setTitle(tripUpdateReqDTO.getTitle());
        trip.setMember(member);

        System.out.println(trip);
        tripRepository.save(trip);

        return false;
    }

    @Override
    public List<ArrayTripResDTO> getByMemberId(String email) {
        Member member = memberRepository.findByEmail(email).get();
       List<Trip> trips =  tripRepository.getByMemberId( member.getId());
       List<ArrayTripResDTO> arrayTripResDTOS = trips.stream().map(trip -> ArrayTripResDTO.builder()
               .tripId(trip.getId())
               .startDate(trip.getStartDate())
                       .endDate(trip.getEndDate())
               .state(trip.getState())
               .title(trip.getTitle())
               .plans(trip.getPlans())
       .build()).collect(Collectors.toList());
return arrayTripResDTOS;
    }
}
