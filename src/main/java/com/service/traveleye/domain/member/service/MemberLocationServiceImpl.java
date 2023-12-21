package com.service.traveleye.domain.member.service;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberLocation;
import com.service.traveleye.domain.member.repository.MemberLocationRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberLocationServiceImpl implements MemberLocationService{

    private final MemberLocationRepository memberLocationRepository;
    @Override
    public DataResDTO<?> setLocation(Member member, Double latitude, Double longitude){
        MemberLocation memberLocation = MemberLocation.builder()
                .member(member)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        return DataResDTO.builder()
                .message("location 설정 완료")
                .data(memberLocationRepository.save(memberLocation))
                .build();
    }

}
