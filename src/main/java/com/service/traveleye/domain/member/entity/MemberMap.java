package com.service.traveleye.domain.member.entity;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.global.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="member_map_test") /* 동행 테이블 */
@NoArgsConstructor
@ToString
public class MemberMap extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id",nullable = false)
    private Member requester;

    @ManyToOne
    @JoinColumn(name = "receiver_id",nullable = false)
    private Member receiver; /* 최소 동행은 2명 이상 */

    @ManyToOne
    @JoinColumn(name="landmark_id",nullable = false)
    private Landmark landmark;

    private State state; // PENDING / ACTIVE / COMPLETE : 별점 쓰면 됨

    @Builder
    public MemberMap(Member requester, Member receiver, State state,Landmark landmark) {
        this.requester = requester;
        this.receiver = receiver;
        this.state = state;
        this.landmark= landmark;
    }
}
