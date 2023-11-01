package com.service.traveleye.domain.survey.controller;

import com.service.traveleye.domain.member.dto.MemberSignupReqDTO;
import com.service.traveleye.domain.survey.dto.SurveyReqDTO;
import com.service.traveleye.domain.survey.service.SurveyService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    @PostMapping("/survey")
    public DataResDTO<?> surveyRegister(SurveyReqDTO surveyReqDTO){
        String email = "user1@gmail.com";
        return surveyService.surveyRegister(email,surveyReqDTO);
    }
}
