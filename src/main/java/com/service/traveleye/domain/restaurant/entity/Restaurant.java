package com.service.traveleye.domain.restaurant.entity;

import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="restaurant_test") /* 맛집 테이블*/
@NoArgsConstructor
@ToString
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}
