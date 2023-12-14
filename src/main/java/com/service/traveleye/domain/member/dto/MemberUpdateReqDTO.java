package com.service.traveleye.domain.member.dto;

import com.service.traveleye.domain.member.entity.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberUpdateReqDTO {

    private String nickname;
    private String introduction;


}
