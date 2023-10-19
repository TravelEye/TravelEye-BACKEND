package com.service.traveleye.domain.survey.service;

import com.service.traveleye.domain.survey.dto.SurveyReqDTO;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{
    @Override
    public DataResDTO<?> surveyRegister(SurveyReqDTO SurveyReqDTO) {
        return null;
    }
}
