package com.service.traveleye.domain.member.dto;

import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupReqDTO {
    private String email;
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .uuid(UUID.randomUUID())
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.USER)
                .shareLocation(false)
                .build();
    }
}
