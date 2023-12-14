package com.service.traveleye.domain.member.controller;

import com.service.traveleye.domain.member.dto.MemberLoginReqDTO;
import com.service.traveleye.domain.member.dto.MemberSignupReqDTO;

import com.service.traveleye.domain.member.dto.MemberUpdateReqDTO;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.service.MemberService;
import com.service.traveleye.domain.refreshToken.dto.TokenReqDTO;
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

    @DeleteMapping("/member/logout")
    public DataResDTO<?> logout(@AuthenticationPrincipal MemberDetails memberDetails){
        return memberService.logout(memberDetails);
    }

    @GetMapping("/member/myinfo")
    public DataResDTO<?> getMyInfo(@AuthenticationPrincipal MemberDetails memberDetails){
        String email = extractMemberEmail(memberDetails);
        return memberService.getMyInfo(email);
    }

    @PatchMapping("/reissue")
    public DataResDTO<?> reissue(@RequestBody TokenReqDTO tokenReqDTO){
        return memberService.reissue(tokenReqDTO);
    }
    @PutMapping ("/member/myinfo")
    public DataResDTO<?> updateMyInfo(@AuthenticationPrincipal MemberDetails memberDetails,@RequestBody MemberUpdateReqDTO memberUpdateReqDTO){
        String email = extractMemberEmail(memberDetails);
        return memberService.updateMyInfo(email,memberUpdateReqDTO);
    }
    @GetMapping("/exists")
    public DataResDTO<?> checkDuplicate(@RequestParam(required = false) String email, @RequestParam(required = false) String nickname) {
        if (email != null) {
            return memberService.checkDuplicateEmail(email);
        } else if (nickname != null) {
            return memberService.checkDuplicateNickname(nickname);
        } else {
            // Handle invalid request (both email and nickname are null)
            return DataResDTO.builder()
                    .message("Invalid request. Specify either 'email' or 'nickname' parameter.")
                    .build();
        }
    }




    private String extractMemberEmail(MemberDetails memberDetails) {
        return memberDetails.getMember().getEmail();
    }
}
