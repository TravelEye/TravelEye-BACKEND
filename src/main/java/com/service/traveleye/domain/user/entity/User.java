package com.service.traveleye.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service.traveleye.global.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="user_test")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID userUuid;
    private String phoneNumber;
    private String email; // 아이디
    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean shareLocation;
    @Column(columnDefinition = "TINYINT")
    private int age;
    private int gender;
    private boolean isAdmin; //관리자

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @JsonManagedReference
    private Survey survey;

   @Builder
   public User( UUID userUuid, String phoneNumber, String email, String password, Boolean shareLocation, int age, int gender, boolean isAdmin) {
       this.userUuid = userUuid;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.password = password;
       this.shareLocation = shareLocation;
       this.age = age;
       this.gender = gender;
       this.isAdmin = isAdmin;
   }
}
