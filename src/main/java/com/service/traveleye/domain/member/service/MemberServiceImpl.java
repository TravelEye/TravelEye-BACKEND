package com.service.traveleye.domain.member.service;


import com.service.traveleye.domain.config.jwt.JwtFilter;
import com.service.traveleye.domain.config.jwt.JwtTokenProvider;
import com.service.traveleye.domain.config.jwt.dto.TokenDTO;
import com.service.traveleye.domain.member.dto.*;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.refreshToken.dto.TokenReqDTO;
import com.service.traveleye.domain.refreshToken.entity.RefreshToken;
import com.service.traveleye.domain.refreshToken.repository.RefreshTokenRepository;
import com.service.traveleye.global.dto.DataResDTO;
import com.service.traveleye.global.exception.BadRequestException;
import com.service.traveleye.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final RefreshTokenRepository refreshTokenRepository;
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

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(member.getUuid().toString()) //rt_key=uuid
                .value(tokenDTO.getRefreshToken()) //rt_value=refresh token
                .build();
        refreshTokenRepository.save(refreshToken);
        log.info("RefreshToken 저장");
        return DataResDTO.builder()
                .message("토큰 생성 완료")
                .data(MemberLoginResDTO.toBuild( tokenDTO))
                .build();
    }

    @Override
    public DataResDTO<?> logout(MemberDetails memberDetails) {
        //memberDetails를 통해, 내 UUID 획득
        System.out.println(memberDetails.toString());
        String myUuid = memberDetails.getMember().getUuid().toString();
        //내 UUID로 리프래시 토큰 서칭
        RefreshToken refreshToken = refreshTokenRepository.findByKey(myUuid).get();

        try {
            //해당 리프래시 토큰 삭제
            refreshTokenRepository.delete(refreshToken);
            log.info("리프래시 토큰 삭제");
            return DataResDTO.builder()
                    .message("로그아웃되었습니다.")
                    .data(true)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoSuchElementException("로그아웃 실패했습니다");
        }

    }
    @Transactional
    public DataResDTO<?> reissue(TokenReqDTO tokenReqDto) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(tokenReqDto.getRefreshToken())) {
            log.info("Refresh Token 이 유효하지 않습니다.");
            return DataResDTO.builder()
                    .status(400)
                    .message("Refresh Token 이 유효하지 않습니다.")
                    .data(TokenDTO.builder()
                            .grantType(null)
                            .accessToken(null)
                            .refreshToken(null)
                            .accessTokenExpiresIn(null)
                            .build())
                    .build();
        }

        // 2. Access Token 에서 authentication 가져오기
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenReqDto.getAccessToken());

        // 3. 저장소에서 Member UUID 를 기반으로 Refresh Token 값 가져옴
        Member member = memberRepository.findByEmail(authentication.getName()).get();
        RefreshToken refreshToken = refreshTokenRepository.findByKey(member.getUuid().toString())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenReqDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDTO tokenDto = jwtTokenProvider.createToken(authentication);
        log.info("새로운 토큰 생성");
        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);
        log.info("저장소 정보 업데이트");

        // 토큰 발급
        return DataResDTO.builder()
                .message("토큰 재생성 완료")
                .data(tokenDto)
                .build();
    }

    @Override
    public DataResDTO<?> checkDuplicateEmail(String email) {
        return DataResDTO.builder()
                        .message("이메일 중복 체크 완료")
                        .data(memberRepository.existsByEmail(email))
                        .build();
    }

    @Override
    public DataResDTO<?> checkDuplicateNickname(String nickname) {
        return DataResDTO.builder()
                .message("닉네임 중복체크 완료")
                .data(memberRepository.findByNickname(nickname))
                .build();
    }

    @Override
    public DataResDTO<?> getMyInfo(String email) {
        return DataResDTO.builder()
                .message("내 프로필정보 가져오기 완료")
                .data(memberRepository.findByEmail(email).get())
                .build();
    }

    @Override
    @Transactional
    @Modifying
    public DataResDTO<?> updateMyInfo(String email, MemberUpdateReqDTO memberUpdateReqDTO) {
        try {
            Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Member not found"));
            System.out.println(memberUpdateReqDTO.toString());
            member.setNickname(memberUpdateReqDTO.getNickname());
            member.setIntroduction(memberUpdateReqDTO.getIntroduction());

            Member updatedMember = memberRepository.save(member);

            return DataResDTO.builder()
                    .message("수정 완료")
                    .data(updatedMember)
                    .build();
        } catch (Exception e) {
            log.error("Error updating member info for email: {}", email, e);
            return DataResDTO.builder()
                    .message("내 프로필 수정 중 오류 발생")
                    .message(e.getMessage())
                    .build();
        }
    }


}
