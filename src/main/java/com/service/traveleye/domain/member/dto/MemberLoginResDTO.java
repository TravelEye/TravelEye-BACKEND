package com.service.traveleye.domain.member.dto;

import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.domain.config.jwt.dto.TokenDTO;
import com.service.traveleye.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginResDTO {
//    private String uuid;
//    private String phoneNumber;
//    private String email;
//    private int age;
//    private String gender;
//    private Boolean shareLocation;
//    private Authority authority;

    private TokenDTO tokenDTO;
    public static MemberLoginResDTO toBuild(TokenDTO tokenDTO){
        return MemberLoginResDTO.builder()
                .tokenDTO(tokenDTO)
                .build();


    }
//    public static MemberLoginResDTO toBuild(Member member , TokenDTO tokenDTO){
//        return MemberLoginResDTO.builder()
//                .uuid(member.getUuid().toString())
//                .phoneNumber(member.getPhoneNumber())
//                .email(member.getEmail())
//                .age(member.getAge())
//                .gender(member.getGender())
//                .shareLocation(member.getShareLocation())
//                .authority(member.getAuthority())
//                .tokenDTO(tokenDTO)
//                .build();
//
//
//    }
}
