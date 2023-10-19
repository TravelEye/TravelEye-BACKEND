package com.service.traveleye.domain;

import com.service.traveleye.domain.member.service.MemberService;
import com.service.traveleye.global.dto.DataResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelEyeBackendApplicationTests {
@Autowired
    MemberService memberService;
    @Test
    void checkUsernameTest() {
        DataResDTO<?> result = memberService.checkDuplicateEmail("user1@gmail.com");
        System.out.println(result.toString());
    }

}
