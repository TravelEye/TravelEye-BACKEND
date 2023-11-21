package com.service.traveleye.domain.country.entity;

import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="country_test")
@NoArgsConstructor
@ToString
public class Country extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String continent;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;
}
