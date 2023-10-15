package com.service.traveleye.global.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataResDTO<T> extends BaseResDTO {
    private T data;

    @Builder
    public DataResDTO(int status, String message, T data) {
        super(status, message);
        this.data = data;
    }
}