package com.service.traveleye.domain.survey.service;

import com.service.traveleye.domain.survey.dto.SurveyReqDTO;
import com.service.traveleye.global.dto.DataResDTO;

public interface SurveyService {
    DataResDTO<?> surveyRegister(SurveyReqDTO SurveyReqDTO);
}
