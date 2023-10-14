package com.service.traveleye.domain.repository;

import com.service.traveleye.domain.entity.Survey;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository

public class SurveyRepository {
    @PersistenceContext // spring container 가 참조
    private EntityManager em;

    public Survey save(Survey survey){
        em.persist(survey);
        return survey;
    }

    public Survey find(Long id){
        return em.find(Survey.class,id);
    }
}
