package com.service.traveleye.domain.member.repository;


import com.service.traveleye.domain.member.entity.MemberLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLocationRepository extends JpaRepository<MemberLocation,Long> {


}
