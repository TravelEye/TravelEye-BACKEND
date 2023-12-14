package com.service.traveleye.domain.config.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    private String grantType; // 인증타입 :: 예시)"Bearer"
    private String accessToken; // 토큰값
    private String refreshToken; // refresh 토큰값
    private Long accessTokenExpiresIn; // 토큰 만료시간

}
