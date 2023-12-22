package com.ssafy.enjoytrip.auth.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class LoginDto {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }

}