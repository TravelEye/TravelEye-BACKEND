package com.service.traveleye.domain.member.repository;

import com.service.traveleye.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email); //null 값 고려
    Optional<Member> findByNickname(String nickname);

    Optional<Member> findMemberById(Long id);

}
