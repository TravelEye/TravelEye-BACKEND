package com.service.traveleye.domain.member.controller;

import com.service.traveleye.domain.member.dto.MemberMapReceiveReqDTO;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.member.service.MemberMapService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberMapController {
    private final MemberMapService memberMapService;
    private final MemberRepository memberRepository;
    @GetMapping("/request/{landmarkId}/{nickname}")
    public DataResDTO<?> request(@AuthenticationPrincipal MemberDetails memberDetails,@PathVariable Long landmarkId, @PathVariable String nickname){
        Member member = memberRepository.findByNickname(nickname).get();
        Long requesterId = memberDetails.getMember().getId();

        return memberMapService.request(requesterId, member.getId(),landmarkId);
    }

    @GetMapping("/request/all")
    public DataResDTO<?> getAllRequests(@AuthenticationPrincipal MemberDetails memberDetails){
        Member member =memberDetails.getMember();
        return memberMapService.getAllRequests(member);
    }
    @GetMapping("/receive/all")
    public DataResDTO<?> getAllReceives(@AuthenticationPrincipal MemberDetails memberDetails){
        Member member =memberDetails.getMember();
        return memberMapService.getAllReceives(member);
    }
    @GetMapping("/history")
    public DataResDTO<?> getHistory(@AuthenticationPrincipal MemberDetails memberDetails){

        Member member =memberDetails.getMember();
        return memberMapService.getHistory(member);
    }

    @GetMapping("/complete/{id}")
    public DataResDTO<?> complete(@AuthenticationPrincipal MemberDetails memberDetails,@PathVariable String id ){
        return memberMapService.complete(Long.valueOf(id));
    }
    @PostMapping("/receive")
    public DataResDTO<?> accept(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody MemberMapReceiveReqDTO memberMapReceiveReqDTO){
        return memberMapService.accept(memberMapReceiveReqDTO.getId());
    }
}
