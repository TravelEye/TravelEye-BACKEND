package com.service.traveleye.domain.refreshToken.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReqDTO {
    private String accessToken;
    private String refreshToken;
 }
