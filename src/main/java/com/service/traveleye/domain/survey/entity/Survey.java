package com.service.traveleye.domain.survey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.BaseEntity;
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
public class Survey extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;

    /* 목적지 */
    private float preferNatureThanCity; // 자연(휴양지) vs 도시
    private float preferNewCity; // 잘 알려지지 않은 방문지 vs 알려진 방문지

    /* 즐기는 스타일 */
    private float preferDetailPlan; // 계획에 따른 여행 vs 상황에 따른 여행
    private float preferTightSchedule; // 빡센 여행 vs 여유았는 여행
    private float preferManyPhotos; // 사진촬영 중요 vs 사진촬영 안중요
    private float preferGoodFood; // 맛집 가는 것 중요 vs 아무 식당 다 좋음
    private float preferShoppingThanTour; // 쇼핑시간 필요 vs 투어만

    /* 숙박 */
    private float preferDayTrip; // 당일치기 vs 숙박
    private float preferCheapHotelThanComfort; // 편하지만 비싼 숙소 vs 불편하지만 저렴한 숙소

    /* 교통 */
    private float preferDirectFlight; // 직항 vs 경유
    private float preferCheapTraffic; // (짧은 거리) 걷기 선호 vs 대중교통 선호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Member member;


}
