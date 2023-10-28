package com.service.traveleye.domain.member.service;

import com.service.traveleye.domain.config.jwt.JwtFilter;
import com.service.traveleye.domain.config.jwt.JwtTokenProvider;
import com.service.traveleye.domain.config.jwt.dto.TokenDTO;
import com.service.traveleye.domain.member.dto.MemberLoginReqDTO;
import com.service.traveleye.domain.member.dto.MemberLoginResDTO;
import com.service.traveleye.domain.member.dto.MemberSignupReqDTO;
import com.service.traveleye.domain.member.dto.MemberSignupResDTO;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.global.dto.DataResDTO;
import com.service.traveleye.global.dto.ErrorMessage;
import com.service.traveleye.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public DataResDTO<?> signup(MemberSignupReqDTO memberSignupReqDTO) {
        log.info("멤버 회원 가입 요청: {}", memberSignupReqDTO.toString());
        if(memberRepository.existsByEmail(memberSignupReqDTO.getEmail()))
            throw new BadRequestException("이미 가입한 회원입니다.");
        Member member = memberSignupReqDTO.toMember(passwordEncoder);
        log.info("멤버 생성: {}", member);
        try{
            memberRepository.save(member);
            return DataResDTO.builder()
                    .message("회원가입이 완료되었습니다")
                    .data(MemberSignupResDTO.toBuild(member))
                    .build();
        }
        catch(Exception e){
            throw new NoSuchElementException("회원가입이 실패했습니다");
        }

    }

    @Override
    public DataResDTO<?> login(MemberLoginReqDTO memberLoginReqDTO) {
        Member member = null;
        member = memberRepository.findByEmail(memberLoginReqDTO.getEmail()).get();
        if (member == null) {
            log.info("아이디가 틀렸습니다.");
            return DataResDTO.builder()
                    .status(410)
                    .message("아이디가 틀렸습니다.")
                    .data(false)
                    .build();
        }
        if (!passwordEncoder.matches(memberLoginReqDTO.getPassword(), member.getPassword())) {
            log.info("비밀번호가 틀렸습니다.");
            return DataResDTO.builder()
                    .status(420)
                    .message("비밀번호가 틀렸습니다.")
                    .data(false)
                    .build();
        }

        //1. Login ID & Password 로 Authentication Token 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginReqDTO.toAuthentication();
        //2. authenticationManager에서 토큰을 검증 후 검증된 토큰을 반환
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //3. SecurityContextHolder에 위에서 생성한 Authentication 객체를 저장한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //4. JwtTokenProvider를 통해 JWT 토큰을 생성한다.
        TokenDTO tokenDTO = jwtTokenProvider.createToken(authentication);

        //4. 생성한 JWT 토큰을 Response Header에 담아서 리턴한다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDTO);

        return DataResDTO.builder()
                .message("토큰 생성 완료")
                .data(MemberLoginResDTO.toBuild(member, tokenDTO))
                .build();
    }

    @Override
    public DataResDTO<?> logout(MemberDetails memberDetails) {
//        //memberDetails를 통해, 내 UUID 획득
//        String myUuid = memberDetails.getMember().getUuid().toString();
//        //내 UUID로 리프래시 토큰 서칭
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(myUuid).get();
//        log.info("내 UUID로 리프래시 토큰 서칭");
//
//        //fcm토큰값 없앰
//        updateFcm(MemberFcmReqDto.builder().fcmToken("").build(), memberDetails);
//        log.info("fcm토큰값 없앰");
//
//        try {
//            //해당 리프래시 토큰 삭제
//            refreshTokenRepository.delete(refreshToken);
//            log.info("리프래시 토큰 삭제");
//            return DataResDTO.builder()
//                    .message("로그아웃되었습니다.")
//                    .data(true)
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new NoSuchElementException("로그아웃 실패했습니다");
//        }
        return null;
    }

    @Override
    public DataResDTO<?> checkDuplicateEmail(String email) {
        return DataResDTO.builder()
                        .message("이메일 중복 체크 완료")
                        .data(memberRepository.existsByEmail(email))
                        .build();
    }
}
