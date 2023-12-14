package com.service.traveleye.domain.member.service;

import com.service.traveleye.domain.member.dto.MemberLoginReqDTO;
import com.service.traveleye.domain.member.dto.MemberSignupReqDTO;
import com.service.traveleye.domain.member.dto.MemberUpdateReqDTO;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.refreshToken.dto.TokenReqDTO;
import com.service.traveleye.global.dto.DataResDTO;


public interface MemberService {
    DataResDTO<?> signup(MemberSignupReqDTO memberSignupReqDTO);
    DataResDTO<?> login(MemberLoginReqDTO memberLoginReqDTO);
    DataResDTO<?> logout(MemberDetails memberDetails);

    DataResDTO<?> checkDuplicateEmail(String email);
    DataResDTO<?> checkDuplicateNickname(String nickname);

    DataResDTO<?> getMyInfo(String email);
    DataResDTO<?> updateMyInfo(String email, MemberUpdateReqDTO memberUpdateReqDTO);

    DataResDTO<?> reissue(TokenReqDTO tokenReqDTO);

}
