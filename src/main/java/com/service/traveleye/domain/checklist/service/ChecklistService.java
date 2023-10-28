package com.service.traveleye.domain.checklist.service;

import com.service.traveleye.domain.checklist.dto.ChecklistReqDTO;
import com.service.traveleye.global.dto.DataResDTO;

public interface ChecklistService {

    DataResDTO<?> searchById(Long id);
    DataResDTO<?> add(ChecklistReqDTO checklistReqDTO, String email);

    DataResDTO<?>  update(ChecklistReqDTO checklistReqDTO , String email);

    void deleteById(Long id);


}