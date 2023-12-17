package com.service.traveleye.domain;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.member.service.MemberService;
import com.service.traveleye.global.dto.DataResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelEyeBackendApplicationTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void checkUsernameTest() {
        DataResDTO<?> result = memberService.checkDuplicateEmail("user1@gmail.com");
        System.out.println(result.toString());
    }
    @Test
    void findMemberTest() {
        Member result = memberRepository.findByEmail("user1@gmail.com").get();
        System.out.println(result);
    }



}
