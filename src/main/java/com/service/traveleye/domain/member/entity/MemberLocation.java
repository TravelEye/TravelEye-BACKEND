package com.service.traveleye.domain.member.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="member_location_test") /* 동행 테이블 */
@NoArgsConstructor
@ToString
public class MemberLocation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    private Double latitude; /* 위도 */
    private Double longitude; /* 경도 */

    @Builder
    public MemberLocation( Member member, Double latitude, Double longitude) {
        this.member = member;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
