package com.service.traveleye.domain.user.dto;

import com.service.traveleye.domain.user.entity.User;
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
public class UserSignupReqDTO {
    private String email;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .userUuid(UUID.randomUUID())
                .email(email)
                .password(passwordEncoder.encode(password))
                .isAdmin(false)
                .shareLocation(false)
                .build();
    }
}
