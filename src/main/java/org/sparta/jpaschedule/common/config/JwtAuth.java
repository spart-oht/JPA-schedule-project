package org.sparta.jpaschedule.common.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.sparta.jpaschedule.user.domain.User;
import org.sparta.jpaschedule.user.domain.UserRoleEnum;
import org.sparta.jpaschedule.common.jwt.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j(topic = "jwt token")
@Component
public class JwtAuth {

    private final JwtUtil jwtUtil;

    public JwtAuth(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public void createJwt(User user, HttpServletResponse res) {
        // Jwt 생성
        String token = jwtUtil.createToken(user.getId(), user.getGrade());

        log.info(token);
        // Jwt 쿠키 저장
        jwtUtil.addJwtToHeader(token, res);
    }

    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        jwtUtil.validateToken(token);

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
