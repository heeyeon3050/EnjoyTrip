package com.ssafy.enjoytrip.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;

    private String userId;

    private Long accessTokenExpirationTime;
    private Long refreshTokenExpirationTime;

    public void updateUserId(String userId) {
        this.userId = userId;
    }

}
