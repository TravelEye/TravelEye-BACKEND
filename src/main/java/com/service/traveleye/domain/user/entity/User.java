package com.service.traveleye.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service.traveleye.domain.survey.entity.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="user_test")
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    private String phoneNumber;
    private String email; // 아이디
    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean shareLocation;
    @Column(columnDefinition = "TINYINT")
    private int age;
    private int gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @JsonManagedReference
    private Survey survey;

}
