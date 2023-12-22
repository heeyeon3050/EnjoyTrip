package com.ssafy.enjoytrip.auth.config.jwt;

import com.ssafy.enjoytrip.auth.exeption.AccessTokenNotValidException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
유효 자격검증을 거치지 않고 접근하려 할 때 401 에러 반환
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        throw new AccessTokenNotValidException("유효 자격검증을 거치지 않았습니다.");
    }
}
