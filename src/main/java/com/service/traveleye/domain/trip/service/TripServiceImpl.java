package com.service.traveleye.domain.trip.service;


import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.landmark.repository.LandmarkRepository;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.trip.dto.ArrayTripResDTO;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripMemoAddReqDTO;

import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.entity.TripMemo;
import com.service.traveleye.domain.trip.repository.TripMemoRepository;
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
private final TripMemoRepository tripMemoRepository;
private final MemberRepository memberRepository;
private final LandmarkRepository landmarkRepository;
    @Override
    public boolean addTrip(String email, TripAddReqDTO tripAddReqDTO) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Member not found"));

        Trip trip = Trip.builder()
                .member(member)
                .title(tripAddReqDTO.getTitle())
                .country(tripAddReqDTO.getCountry())
                .startDate(tripAddReqDTO.getStartDate())
                .endDate(tripAddReqDTO.getEndDate())
                .state(State.valueOf("PENDING"))
                .build();

        tripRepository.save(trip);

        //trip memo
        List<TripMemoAddReqDTO> tripMemos = tripAddReqDTO.getMemos();
        for (TripMemoAddReqDTO tripMemo : tripMemos) {
            Landmark landmark = landmarkRepository.findByLandmarkId(tripMemo.getLandmarkId());
            TripMemo newTripmemo = TripMemo.builder()
                                    .memo(tripMemo.getMemo())
                                    .date(tripMemo.getDate())
                                    .landmark(landmark)
                                    .trip(trip)
                                    .build();
            tripMemoRepository.save(newTripmemo);
        }

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
        Trip trip = tripRepository.getById(tripUpdateReqDTO.getTripId());
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Member not found"));

        trip.setTripId(tripUpdateReqDTO.getTripId());
        trip.setCountry(tripUpdateReqDTO.getCountry());
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
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Member not found"));

        List<Trip> trips = tripRepository.getByMemberId(member.getId());

        List<ArrayTripResDTO> arrayTripResDTOS = trips.stream().map(trip -> {
            List<TripMemo> memos = tripMemoRepository.findByTripTripId(trip.getTripId());

            return ArrayTripResDTO.builder()
                    .tripId(trip.getTripId())
                    .startDate(trip.getStartDate())
                    .endDate(trip.getEndDate())
                    .state(trip.getState())
                    .title(trip.getTitle())
                    .country(trip.getCountry())
                    .memos(memos)
                    .build();
        }).collect(Collectors.toList());

        return arrayTripResDTOS;
    }
    }

