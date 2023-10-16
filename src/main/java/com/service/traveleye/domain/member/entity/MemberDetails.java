package com.service.traveleye.domain.member.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class MemberDetails extends User {
    private final Member member;
    public MemberDetails(Member member, GrantedAuthority grantedAuthority){
        super(member.getEmail(),member.getPassword(), Collections.singleton(grantedAuthority));
        this.member = member;
    }
}
