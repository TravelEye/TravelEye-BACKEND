package com.service.traveleye.domain;

import com.service.traveleye.domain.survey.entity.Survey;
import com.service.traveleye.domain.survey.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class SurveyTest {

    @Autowired
    SurveyRepository surveyRepository;
    @Test
     public void saveSurvey() {
        Survey survey = new Survey("Korea");
        Survey savedSurvey = surveyRepository.save(survey);
        Survey findSurvey = surveyRepository.findById(savedSurvey.getId()).get();
        System.out.println(findSurvey.getId().equals(survey.getId()));
        System.out.println(findSurvey.equals(survey));
    }

}