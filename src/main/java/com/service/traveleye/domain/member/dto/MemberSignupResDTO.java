package com.service.traveleye.domain.member.dto;

import com.service.traveleye.domain.config.entity.Authority;
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
    private String gender;
    private Boolean shareLocation;
    private Authority authority;

    public static MemberSignupResDTO toBuild(Member member){
        return MemberSignupResDTO.builder()
                .uuid(member.getUuid().toString())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .age(member.getAge())
                .gender(member.getGender())
                .shareLocation(member.getShareLocation())
                .authority(member.getAuthority())
                .build();


    }

}
