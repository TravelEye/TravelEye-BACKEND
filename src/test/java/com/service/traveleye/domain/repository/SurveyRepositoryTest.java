package com.service.traveleye.domain.repository;

import com.service.traveleye.domain.entity.Survey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class SurveyRepositoryTest {

    @Autowired SurveyRepository surveyRepository;
    @Test
     public void saveSurvey() {
        Survey survey = new Survey("Korea");
        Survey savedSurvey = surveyRepository.save(survey);

        Survey findSurvey = surveyRepository.find(savedSurvey.getId());
        System.out.println(findSurvey.getId().equals(survey.getId()));
        System.out.println(findSurvey.getCountry().equals(survey.getCountry()));
    }

}