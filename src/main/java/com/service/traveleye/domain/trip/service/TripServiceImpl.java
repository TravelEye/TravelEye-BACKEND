package com.service.traveleye.domain.trip.service;


import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.landmark.repository.LandmarkRepository;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.trip.dto.ArrayTripResDTO;
import com.service.traveleye.domain.trip.dto.TripAddReqDTO;
import com.service.traveleye.domain.trip.dto.TripMemoAddReqDTO;

import com.service.traveleye.domain.trip.dto.TripUpdateReqDTO;
import com.service.traveleye.domain.trip.entity.Trip;
import com.service.traveleye.domain.trip.entity.TripMemo;
import com.service.traveleye.domain.trip.repository.TripMemoRepository;
import com.service.traveleye.domain.trip.repository.TripRepository;
import com.service.traveleye.global.dto.DataResDTO;
import com.service.traveleye.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public DataResDTO<?> addTrip( Long id,TripAddReqDTO tripAddReqDTO) {
    Member member = memberRepository.findMemberById(id).get();

        Trip trip = Trip.builder()
                .member(member)
                .title(tripAddReqDTO.getTitle())
                .country(tripAddReqDTO.getCountry())
                .city(tripAddReqDTO.getCity())
                .startDate(tripAddReqDTO.getStartDate())
                .endDate(tripAddReqDTO.getEndDate())
                .state(State.valueOf("PENDING"))
                .build();

        Trip result1 = tripRepository.save(trip);

        //trip memo
        Optional<List<TripMemoAddReqDTO>> optionalMemos = tripAddReqDTO.getMemos();
        System.out.println(optionalMemos);
        if (optionalMemos != null) {
            List<TripMemoAddReqDTO> tripMemos = optionalMemos.get();
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
        }

        return DataResDTO.builder()
                .message("trip 만들기 성공")
                .data(result1)
                .build();
    }



    @Override
    @Transactional
    public DataResDTO<?> deleteTripById(Long id) {
        try {
            Trip trip = tripRepository.findById(id).get();
            List<TripMemo> memos = tripMemoRepository.deleteTripMemosByTrip(trip);
             tripRepository.deleteById(id);

            return DataResDTO.builder()
                    .message("삭제 성공")
                    .data(memos)
                    .build();
        } catch (EmptyResultDataAccessException ex) {
            return DataResDTO.builder()
                    .message("삭제 실패")
                    .build();
        }
    }

    @Override
    @Transactional
    @Modifying
    public DataResDTO<?> updateTrip(Long id, TripUpdateReqDTO tripUpdateReqDTO) {
        Trip trip = tripRepository.getById(tripUpdateReqDTO.getTripId());
        List<TripMemo> existingMemos = tripMemoRepository.findByTripTripId(tripUpdateReqDTO.getTripId());


        Member member = memberRepository.findMemberById(id).get();

        trip.setTripId(tripUpdateReqDTO.getTripId());
        trip.setCountry(tripUpdateReqDTO.getCountry());
        trip.setCity(tripUpdateReqDTO.getCity());
        trip.setState(trip.getState());
        trip.setStartDate(tripUpdateReqDTO.getStartDate());
        trip.setEndDate(tripUpdateReqDTO.getEndDate());
        trip.setTitle(tripUpdateReqDTO.getTitle());
        trip.setMember(member);

        // Remove existing memos
        tripMemoRepository.deleteAll(existingMemos);
        Trip updatedTrip = tripRepository.save(trip);

// Update existing memos or add new ones
        for (TripMemoAddReqDTO tripMemoDTO : tripUpdateReqDTO.getMemos()) {
            Landmark landmark = landmarkRepository.findByLandmarkId(tripMemoDTO.getLandmarkId());

            // Check if a memo already exists for the landmark
            TripMemo existingMemo = tripMemoRepository.findByTripAndLandmark(updatedTrip, landmark);

            if (existingMemo != null) {
                // Update existing memo if there's information change
                if (!existingMemo.getMemo().equals(tripMemoDTO.getMemo()) || !existingMemo.getDate().equals(tripMemoDTO.getDate())) {
                    existingMemo.setMemo(tripMemoDTO.getMemo());
                    existingMemo.setDate(tripMemoDTO.getDate());
                    tripMemoRepository.save(existingMemo);
                }
            } else {
                // Create a new memo if it doesn't exist
                TripMemo newTripMemo = TripMemo.builder()
                        .memo(tripMemoDTO.getMemo())
                        .date(tripMemoDTO.getDate())
                        .landmark(landmark)
                        .trip(updatedTrip)
                        .build();
                tripMemoRepository.save(newTripMemo);
            }
        }


        return DataResDTO.builder()
                .message("여정 업데이트 완료")
                .data(updatedTrip)
                .build();
    }

    @Override
    public DataResDTO<?> getByMemberId(Long id) {

        List<Trip> trips = tripRepository.getByMemberId(id);

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

        return DataResDTO.builder()
                .message("Id로 여정 조회 성공")
                .data(arrayTripResDTOS)
                .build();
    }
    }

