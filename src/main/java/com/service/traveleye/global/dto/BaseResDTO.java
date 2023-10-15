package com.service.traveleye.global.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseResDTO {

    private int status;
    private String message;

    public BaseResDTO(int status, String message) {
        this.status = status == 0 ? 200 : status;
        this.message = message;
    }
}
