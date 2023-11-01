package com.service.traveleye.domain.checklist.service;

import com.service.traveleye.domain.checklist.dto.ChecklistReqDTO;
import com.service.traveleye.domain.checklist.dto.ChecklistUpdateReqDTO;
import com.service.traveleye.domain.checklist.entity.Checklist;
import com.service.traveleye.domain.checklist.repository.ChecklistRepository;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<Checklist> getChecklists(String email) {
        Member member = memberRepository.findByEmail(email).get();
        return checklistRepository.findByMemberId(member.getId());
    }

    @Override
    @Transactional
    public DataResDTO<?> addChecklist(String title , String email) {
        Member member = memberRepository.findByEmail(email).get();
        Checklist checklist = Checklist.builder()
                .title(title)
                .completed(false)
                .member(member).build();
        checklistRepository.save(checklist);
        return null;
    }

    @Override
    @Transactional
    public DataResDTO<?> updateChecklist(Long id, ChecklistUpdateReqDTO checklistUpdateReqDTO , String email) {
        Member member = memberRepository.findByEmail(email).get();
        // member 가 valid 한 member 인지 체크가 필요
            Checklist checklist = checklistRepository.findById(id).get();
            if (checklist != null) {
                checklist.setTitle(checklistUpdateReqDTO.getTitle());
                checklist.setCompleted(checklistUpdateReqDTO.getCompleted());
                checklistRepository.save(checklist);
            } else {
                // Handle the case where the checklist is null
                // This might mean that there's a problem with the data or the relationship between Member and Checklist
            }


        return null;
    }

    @Override
    @Transactional
    public Integer deleteById(Long id) {
        Integer result = checklistRepository.deleteChecklistByChecklistId(id);
        return result;
    }
}
