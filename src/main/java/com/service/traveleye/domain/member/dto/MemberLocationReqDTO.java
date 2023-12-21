package com.service.traveleye.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberLocationReqDTO {
    private Double latitude; /* 위도 */
    private Double longitude; /* 경도 */
}
