package com.service.traveleye.domain.config.jwt;

import com.service.traveleye.domain.config.CustomUserDetailsService;
import com.service.traveleye.domain.config.jwt.dto.TokenDTO;
import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.global.exception.UnauthorizationException;
import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Slf4j
@Component
public class JwtTokenProvider implements InitializingBean {
    private static final String AUTHORITIES_KEY = "auth";

    private static final String BEARER_TYPE = "Bearer";

    private final String secretKey;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;//1일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
    private Key key;

    private final CustomUserDetailsService customUserDetailsService;
    private final MemberRepository memberRepository;

    public JwtTokenProvider(@Value("${spring.jwt.secret}") String secretKey, CustomUserDetailsService customUserDetailsService, MemberRepository memberRepository) {
        this.secretKey = secretKey;
        this.customUserDetailsService = customUserDetailsService;
        this.memberRepository = memberRepository;
    }


    // 1. Bean 생성 후 주입 받은 후에

    // 2. secret 값을 Base64로 디코딩해 Key변수에 할당
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Authentication 객체의 권한 정보를 이용해 토큰 생성
     *
     * @param authentication - Authentication 객체
     * @return - 토큰
     */
    public TokenDTO createToken(Authentication authentication) {
        //권한 값을 받아 하나의 문자열로 합침
        String authorities = authentication.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.joining(","));

        long now = new Date().getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())       // payload "sub": "phoneNumber"
                .claim(AUTHORITIES_KEY, authorities)        // payload "auth": "ROLE_USER"
                .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
                .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        //토큰 생성
        Member member = memberRepository.findByEmail(authentication.getName()).get();
        return TokenDTO.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 토큰에서 인증 정보 조회 후 Authentication 객체 리턴
     *
     * @param token
     * @return
     */
    //토큰 -> 클레임 추출 -> 유저 객체 제작 -> Authentication 객체 리턴
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        if(claims.get(AUTHORITIES_KEY) == null){
            throw new UnauthorizationException("권한이 없습니다");
        }

        List<? extends SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails= customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    /**
     * 필터에서 사용할 토큰 검증
     *
     * @param token 필터 정보
     * @return 토큰이 유효 여부
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}