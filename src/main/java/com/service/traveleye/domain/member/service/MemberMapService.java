package com.service.traveleye.domain.member.service;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.global.dto.DataResDTO;

public interface MemberMapService {
    DataResDTO<?> getAllRequests(Member member);
    DataResDTO<?> getAllReceives(Member member);
    DataResDTO<?> getHistory(Member member);
    DataResDTO<?> request(Long requesterId, Long receiverId, Long landmarkId);
    DataResDTO<?> complete(Long id);

    DataResDTO<?> accept(Long memberMapId);


}
