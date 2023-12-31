package com.service.traveleye.domain.restaurant.entity;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="favorite_test") /* 맛집 테이블*/
@NoArgsConstructor
@ToString
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

//    @ManyToOne
//    @JoinColumn(name = "restaurant_id",nullable = false)
//    private Restaurant restaurant;

    private String restaurantId;
    private String restaurantName;
    @Builder
    public Favorite(Long id, Member member,String restaurantId, String restaurantName) {
        this.id = id;
        this.member = member;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }
}
