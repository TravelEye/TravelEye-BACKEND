package com.service.traveleye.domain.member.controller;

import com.service.traveleye.domain.member.dto.MemberLoginReqDTO;
import com.service.traveleye.domain.member.dto.MemberSignupReqDTO;

import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.service.MemberService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/users")
public class MemberController {

   private final MemberService memberService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/signup")
    public DataResDTO<?> signup(@RequestBody MemberSignupReqDTO memberSignupReqDTO){
        return memberService.signup(memberSignupReqDTO);
    }


    @PostMapping("/login")
    public DataResDTO<?> login(@RequestBody MemberLoginReqDTO memberLoginReqDTO) {
        return memberService.login(memberLoginReqDTO);
    }

    @DeleteMapping("/logout")
    public DataResDTO<?> logout(@AuthenticationPrincipal MemberDetails memberDetails){
        return memberService.logout(memberDetails);
    }
    @GetMapping("/{username}")
    public DataResDTO<?> CheckDuplicateUsername(@PathVariable String username){
        return memberService.checkDuplicateUsername(username);
    }

}
