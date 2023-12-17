package com.service.traveleye.domain.member.entity;

import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.global.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="member_test")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;
    private String phoneNumber;
    private String email; // 아이디
    private String password;

    @Column
    private Boolean shareLocation;
    @Column(columnDefinition = "TINYINT")
    private int age;
    private String gender; // FEMALE 또는 MALE (둘중 하나)
    private Authority authority; // ADMIN 또는 USER (둘중 하나)
    private Double temperature; //여행온도
    private String introduction;
    private String nickname; //별명

    /* 목적지 */
    private float preferNatureThanCity; // 자연(휴양지) vs 도시
    private float preferNewCity; // 잘 알려지지 않은 방문지 vs 알려진 방문지

    /* 즐기는 스타일 */
    private float preferDetailPlan; // 계획에 따른 여행 vs 상황에 따른 여행
    private float preferTightSchedule; // 빡센 여행 vs 여유았는 여행
    private float preferManyPhotos; // 사진촬영 중요 vs 사진촬영 안중요
    private float preferGoodFood; // 맛집 가는 것 중요 vs 아무 식당 다 좋음

    /* 숙박 */
    private float preferDayTrip; // 당일치기 vs 숙박
    private float preferCheapHotelThanComfort; // 편하지만 비싼 숙소 vs 불편하지만 저렴한 숙소


   @Builder
    public Member(Long id, UUID uuid, String phoneNumber, String email, String password, Boolean shareLocation, int age, String gender, Authority authority, Double temperature, String introduction, float preferNatureThanCity, float preferNewCity, float preferDetailPlan, float preferTightSchedule, float preferManyPhotos, float preferGoodFood, float preferDayTrip, float preferCheapHotelThanComfort, String nickname) {
        this.id = id;
        this.uuid = uuid;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.shareLocation = shareLocation;
        this.age = age;
        this.gender = gender;
        this.authority = authority;
        this.temperature = temperature;
        this.introduction = introduction;
        this.preferNatureThanCity = preferNatureThanCity;
        this.preferNewCity = preferNewCity;
        this.preferDetailPlan = preferDetailPlan;
        this.preferTightSchedule = preferTightSchedule;
        this.preferManyPhotos = preferManyPhotos;
        this.preferGoodFood = preferGoodFood;
        this.preferDayTrip = preferDayTrip;
        this.preferCheapHotelThanComfort = preferCheapHotelThanComfort;
        this.nickname=nickname;
    }
}
