package com.service.traveleye.domain.checklist.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="checklist_test")
@NoArgsConstructor
@ToString
public class Checklist extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false)
    private Long checklist_id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean completed;
    @Builder
    public Checklist( Member member, String title, Boolean completed) {

        this.member = member;
        this.title=title;
        this.completed=completed;
    }

}
