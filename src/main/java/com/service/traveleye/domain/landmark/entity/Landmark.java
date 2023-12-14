package com.service.traveleye.domain.landmark.entity;

import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
@Getter
@Table(name="landmark_test") /* 여행지를 관리하는 테이블 */
@NoArgsConstructor
@ToString
public class Landmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long landmarkId;

    @Column(nullable = false)
    private String continent;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String place; /* 장소 이름 */

    private Double latitude; /* 위도 */
    private Double longitude; /* 경도 */

    private String address; /* 도로명 주소 */
}
