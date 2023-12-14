package com.service.traveleye.domain.member.repository;

import com.service.traveleye.domain.config.entity.State;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapRepository extends JpaRepository<MemberMap,Long> {
    List<MemberMap> findMemberMapByRequesterIdAndState(Long requesterId, State state);
    List<MemberMap> findMemberMapByReceiverIdAndState(Long receiverId, State state);
    MemberMap findMemberMapById(Long id);


}
