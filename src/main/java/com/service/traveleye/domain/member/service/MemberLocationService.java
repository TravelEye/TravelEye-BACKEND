package com.service.traveleye.domain.member.service;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.dto.DataResDTO;

public interface MemberLocationService {
    DataResDTO<?> setLocation(Member member, Double latitude, Double longitude);
    DataResDTO<?> getLocation(Member member);
}
