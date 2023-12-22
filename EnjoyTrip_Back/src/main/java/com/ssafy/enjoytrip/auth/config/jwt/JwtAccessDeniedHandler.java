package com.ssafy.enjoytrip.auth.config.jwt;

import com.ssafy.enjoytrip.auth.exeption.AccessTokenNotValidException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 필요한 권한이 존재하지 않는 경우에 403 에러 반환
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException {
        throw new AccessTokenNotValidException("필요한 권한이 존재하지 않습니다.");
    }

}