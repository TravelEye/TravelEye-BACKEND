package com.service.traveleye.domain.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistResDTO {
    private Long id;

    private String title;

    private Boolean completed;
}