package com.service.traveleye.domain.destination.entity;

import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="destination_test")
@NoArgsConstructor
@ToString
public class Destination extends BaseEntity {
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
