package com.service.traveleye.domain.survey.service;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.survey.dto.SurveyReqDTO;
import com.service.traveleye.domain.survey.entity.Survey;
import com.service.traveleye.domain.survey.repository.SurveyRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;
    @Override
    public DataResDTO<?> surveyRegister(String email,SurveyReqDTO surveyReqDTO) {
        Member member = memberRepository.findByEmail(email).get();
        Survey survey = Survey.builder()
                .preferGoodFood(surveyReqDTO.getPreferGoodFood())
                .preferDayTrip(surveyReqDTO.getPreferDayTrip())
                .preferManyPhotos(surveyReqDTO.getPreferManyPhotos())
                .preferNatureThanCity(surveyReqDTO.getPreferNatureThanCity())
                .preferNewCity(surveyReqDTO.getPreferNewCity())
                .preferTightSchedule(surveyReqDTO.getPreferTightSchedule())
                .preferDetailPlan(surveyReqDTO.getPreferDetailPlan())
                .preferCheapHotelThanComfort(surveyReqDTO.getPreferCheapHotelThanComfort())
                .member(member).build();
        surveyRepository.save(survey);
        return null;
    }
}
