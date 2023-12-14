package com.service.traveleye.domain.member.dto;

import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.domain.config.entity.Gender;
import com.service.traveleye.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class MemberSignupResDTO {

    private String uuid;
    private String phoneNumber;
    private String email;
    private int age;
    private Gender gender;
    private Boolean shareLocation;
    private Authority authority;
    private String nickname;

    public static MemberSignupResDTO toBuild(Member member){
        return MemberSignupResDTO.builder()
                .uuid(member.getUuid().toString())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .age(member.getAge())
                .gender(Gender.valueOf(member.getGender()))
                .shareLocation(member.getShareLocation())
                .authority(member.getAuthority())
                .nickname(member.getNickname())
                .build();


    }

}
