package com.service.traveleye.domain;

import com.service.traveleye.domain.checklist.dto.ChecklistReqDTO;
import com.service.traveleye.domain.checklist.entity.Checklist;
import com.service.traveleye.domain.checklist.service.ChecklistService;
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
    private ChecklistService checklistService;
    @Test
    void checkUsernameTest() {
        DataResDTO<?> result = memberService.checkDuplicateEmail("user1@gmail.com");
        System.out.println(result.toString());
    }

    @Test
    void addChecklist(){
        ChecklistReqDTO checklistReqDTO = ChecklistReqDTO.builder().id(1L).title("Test 1").completed(false).build();

        DataResDTO<?> result = checklistService.add(checklistReqDTO,"user1@gmail.com");
        System.out.println(result);
    }

    @Test
    void updateChecklist(){
        ChecklistReqDTO checklistReqDTO = ChecklistReqDTO.builder().id(1L).title("Test 1 update").completed(true).build();
        DataResDTO<?> result = checklistService.update(checklistReqDTO,"user1@gmail.com");
        System.out.println(result);
    }

    @Test
    void deleteChecklist(){
       checklistService.deleteById(2L);
    }
}
