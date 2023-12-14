package com.service.traveleye.domain.member.dto;

import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.domain.config.entity.Gender;
import com.service.traveleye.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupReqDTO {
    private String email;
    private String password;

    private Boolean shareLocation;
    private int age;
    private String phoneNumber;
    private Gender gender;
    private String nickname;
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

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .uuid(UUID.randomUUID())
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.USER)
                .age(age)
                .nickname(nickname)
                .temperature(36.5)
                .phoneNumber(phoneNumber)
                .introduction("")
                .gender(String.valueOf(gender))
                .shareLocation(shareLocation)
                .preferDayTrip(preferDayTrip)
                .preferDetailPlan(preferDetailPlan)
                .preferGoodFood(preferGoodFood)
                .preferManyPhotos(preferManyPhotos)
                .preferNatureThanCity(preferNatureThanCity)
                .preferCheapHotelThanComfort(preferCheapHotelThanComfort)
                .preferNewCity(preferNewCity)
                .preferTightSchedule(preferTightSchedule)
                .build();
    }
}
