package com.service.traveleye.domain.checklist.controller;

import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/users")
public class ChecklistController {
    @GetMapping("/checklist")
    public DataResDTO<?> getCheckList(){
        return null;
    }

    @PostMapping("/checklist")
    public DataResDTO<?> addCheckList(){
        return null;
    }

    @PutMapping("/checklist")
    public DataResDTO<?> updateCheckList(){
        return null;
    }

    @DeleteMapping("/checklist")
    public DataResDTO<?> deleteCheckList(){
        return null;
    }
}
