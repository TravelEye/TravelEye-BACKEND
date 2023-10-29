package com.service.traveleye.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service.traveleye.domain.checklist.entity.Checklist;
import com.service.traveleye.domain.config.entity.Authority;
import com.service.traveleye.domain.survey.entity.Survey;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;
    private String phoneNumber;
    private String email; // 아이디
    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean shareLocation;
    @Column(columnDefinition = "TINYINT")
    private int age;
    private String gender;
    private Authority authority; // ADMIN 또는 USER (둘중 하나)

//    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn
//    @JsonManagedReference
//    private Survey survey;

//    @OneToMany(mappedBy = "member")
//    private List<Checklist> checklist;

   @Builder
   public Member(UUID uuid, String phoneNumber, String email, String password, Boolean shareLocation, int age, String gender, Authority authority) {
       this.uuid = uuid;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.password = password;
       this.shareLocation = shareLocation;
       this.age = age;
       this.gender = gender;
       this.authority = authority;
   }


}
