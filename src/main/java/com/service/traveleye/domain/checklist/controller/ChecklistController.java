package com.service.traveleye.domain.checklist.controller;

import com.service.traveleye.domain.checklist.dto.ChecklistAddReqDTO;
import com.service.traveleye.domain.checklist.dto.ChecklistDeleteReqDTO;
import com.service.traveleye.domain.checklist.dto.ChecklistReqDTO;
import com.service.traveleye.domain.checklist.entity.Checklist;
import com.service.traveleye.domain.checklist.service.ChecklistService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ChecklistController {

    private final ChecklistService checklistService;
    @GetMapping("/checklist")
    public List<Checklist> getCheckList(){
        String email = "user1@gmail.com";
        List<Checklist> checklists =  checklistService.getChecklists(email);
        return checklists;
    }

    @PostMapping("/checklist")
    public DataResDTO<?> addCheckList(@RequestBody ChecklistAddReqDTO checklistAddReqDTO){
        String email = "user1@gmail.com";
        checklistService.add(checklistAddReqDTO.getTitle(),email);
        return null ;
    }

    @PutMapping("/checklist")
    public DataResDTO<?> updateCheckList(ChecklistReqDTO checklistReqDTO){


        return null;
    }

    @DeleteMapping("/checklist/{id}")
    public Integer deleteCheckList(@PathVariable Long id){  
        System.out.println("id: " + id);
        Integer result = checklistService.deleteById(id);
        return result;
    }

}
