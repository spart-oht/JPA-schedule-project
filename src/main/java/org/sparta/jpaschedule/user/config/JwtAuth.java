package org.sparta.jpaschedule.user.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.sparta.jpaschedule.user.domain.User;
import org.sparta.jpaschedule.user.domain.UserRoleEnum;
import org.sparta.jpaschedule.user.jwt.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Slf4j(topic = "jwt token")
@Component
public class JwtAuth {

    private final JwtUtil jwtUtil;

    public JwtAuth(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public void createJwt(User user, HttpServletResponse res) {
        // Jwt 생성
        String token = jwtUtil.createToken(user.getId(), UserRoleEnum.USER);

        log.info(token);
        // Jwt 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);
    }

    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // 사용자 username
        String username = info.getSubject();
        System.out.println("username = " + username);
        // 사용자 권한
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("authority = " + authority);

        return "getJwt : " + username + ", " + authority;
    }

    @GetMapping("/test")
    public String test() {
        return UserRoleEnum.USER.getAuthority();
    }

}
