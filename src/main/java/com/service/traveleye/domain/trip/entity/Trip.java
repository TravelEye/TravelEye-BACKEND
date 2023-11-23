package com.service.traveleye.domain.trip.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.destination.entity.Destination;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.description.method.MethodDescription;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name="trip_test")
@NoArgsConstructor
@ToString
public class Trip  extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="destination_id")
    private Destination destination; //일단은 다중선택 아니라는 전제

    private String title; // 여정별명
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Lob
    @Column(name = "plans", columnDefinition = "TEXT")
    private String plans; // Store JSON representation as a string

    public List<Map<String, String>> getPlansList() {
        if (plans != null) {
            return new Gson().fromJson(plans, new TypeToken<List<Map<String, String>>>() {}.getType());
        } else {
            return new ArrayList<>();
        }
    }

    public void setPlansList(List<Map<String, String>> plansList) {
        this.plans = new Gson().toJson(plansList);
    }

    private State state; // 여행전 | 여행중 | 여행후
}
