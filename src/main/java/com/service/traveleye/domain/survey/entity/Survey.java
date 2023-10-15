package com.service.traveleye.domain.survey.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="survey_test")
@NoArgsConstructor
@ToString
public class Survey {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String country;

    public Survey( String country) {
        this.country = country;
    }
}
