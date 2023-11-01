package com.service.traveleye.domain.checklist.service;

import com.service.traveleye.domain.checklist.dto.ChecklistReqDTO;
import com.service.traveleye.domain.checklist.dto.ChecklistUpdateReqDTO;
import com.service.traveleye.domain.checklist.entity.Checklist;
import com.service.traveleye.global.dto.DataResDTO;

import java.util.List;

public interface ChecklistService {

    List<Checklist> getChecklists(String email);
    DataResDTO<?> addChecklist(String title, String email);

    DataResDTO<?>  updateChecklist(Long id,ChecklistUpdateReqDTO checklistUpdateReqDTO , String email);

    Integer deleteById(Long id);


}