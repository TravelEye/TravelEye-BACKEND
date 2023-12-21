package com.service.traveleye.domain.member.controller;

import com.service.traveleye.domain.member.dto.MemberLocationReqDTO;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.service.MemberLocationService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberLocationController {

    private final MemberLocationService memberLocationService;
    @PostMapping("/location")
    public DataResDTO<?> setLocation(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody MemberLocationReqDTO memberLocationReqDTO){
        Member member = memberDetails.getMember();
        return memberLocationService.setLocation(member,memberLocationReqDTO.getLatitude(), memberLocationReqDTO.getLongitude());
    }
}
