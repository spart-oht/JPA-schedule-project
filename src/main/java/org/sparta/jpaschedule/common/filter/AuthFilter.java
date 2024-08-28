package org.sparta.jpaschedule.common.filter;


import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.sparta.jpaschedule.common.exception.CommonException;
import org.sparta.jpaschedule.user.domain.User;
import org.sparta.jpaschedule.user.jwt.JwtUtil;
import org.sparta.jpaschedule.user.repository.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (
                        // 허용 목록
                        url.startsWith("/api/user/login") ||
                                url.startsWith("/api/user/edit") ||
                                url.startsWith("/css") ||
                                url.startsWith("/js") ||
                                url.contains("swagger") ||
                                url.contains("api-docs")
                )
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                jwtUtil.validateToken(token);

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                log.info("token 검증 사용자 조회 start");
                User user = userRepository.findById(Long.parseLong(info.getSubject())).orElseThrow(() ->
                        new CommonException(HttpStatus.BAD_REQUEST, "해당 id 로 등록된 회원이 존재하지 않습니다.")
                );
                log.info("token 검증 사용자 조회 end");

                request.setAttribute("user", user);
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new CommonException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
            }
        }
    }

}