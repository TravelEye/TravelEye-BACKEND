package com.service.traveleye.domain.checklist.dto;

import lombok.*;

@ToString
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class ChecklistReqDTO {
    private Long id;
    private String title;
    private Boolean completed;

    @Builder
    public ChecklistReqDTO(Long id, String title, Boolean completed){
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}

