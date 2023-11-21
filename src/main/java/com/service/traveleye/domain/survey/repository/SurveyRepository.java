package com.service.traveleye.domain.survey.repository;
import com.service.traveleye.domain.survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {
    Survey findByMemberId(Long id);


}