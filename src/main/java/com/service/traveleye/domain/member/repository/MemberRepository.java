package com.service.traveleye.domain.member.repository;

import com.service.traveleye.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email); //null 값 고려

}
