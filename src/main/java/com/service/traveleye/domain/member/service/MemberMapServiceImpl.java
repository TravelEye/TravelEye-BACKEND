package com.service.traveleye.domain.member.service;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.landmark.repository.LandmarkRepository;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberMap;
import com.service.traveleye.domain.member.repository.MemberMapRepository;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberMapServiceImpl implements MemberMapService{
    private final MemberMapRepository memberMapRepository;
    private final MemberRepository memberRepository;
    private final LandmarkRepository landmarkRepository;
    @Override
    public DataResDTO<?> getAllRequests(Member member) {
        return DataResDTO.builder()
                .message("내가 보낸 요청")
                .data(memberMapRepository.findMemberMapByRequesterIdAndState(member.getId(), State.valueOf("PENDING")))
                .build();
    }

    @Override
    public DataResDTO<?> getAllReceives(Member member) {
        return DataResDTO.builder()
                .message("내가 받은 요청")
                .data(memberMapRepository.findMemberMapByReceiverIdAndState(member.getId(),State.valueOf("PENDING")))
                .build();
    }

    @Override
    public DataResDTO<?> getHistory(Member member) {
        System.out.println(memberMapRepository.findMemberMapByRequesterIdAndState(member.getId(), State.valueOf("COMPLETED")));
        return DataResDTO.builder()
                .message("동행 히스토리")
                .data(memberMapRepository.findMemberMapByRequesterIdAndState(member.getId(), State.valueOf("COMPLETED")))
                .build();
    }

    @Override
    public DataResDTO<?> request(Long requesterId, Long receiverId,Long landmarkId) {
        Member requester = memberRepository.findMemberById(requesterId).get();
        Member receiver = memberRepository.findMemberById(receiverId).get();
        Landmark landmark = landmarkRepository.findByLandmarkId(landmarkId);
        MemberMap memberMap = MemberMap.builder()
                .requester(requester)
                .receiver(receiver)
                .state(State.valueOf("PENDING"))
                .landmark(landmark)
                .build();


        return DataResDTO.builder()
                .message("동행 요청 완료")
                .data(memberMapRepository.save(memberMap))
                .build();
    }

    @Override
    public DataResDTO<?> complete(Long id) {
        MemberMap memberMap = memberMapRepository.findMemberMapById(id);
        memberMap.setState(State.valueOf("COMPLETED"));
        return DataResDTO.builder()
                .message("동행과의 여행 완료")
                .data(memberMapRepository.save(memberMap))
                .build();
    }

    @Override
    public DataResDTO<?> accept(Long memberMapId) {

       MemberMap memberMap = memberMapRepository.findMemberMapById(memberMapId);
       memberMap.setState(State.valueOf("ACTIVE"));
       return DataResDTO.builder()
               .message("수락 완료")
               .data(memberMapRepository.save(memberMap))
               .build();
    }
}
