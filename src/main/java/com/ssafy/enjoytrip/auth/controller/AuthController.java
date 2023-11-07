package com.ssafy.enjoytrip.auth.controller;

import com.ssafy.enjoytrip.auth.dto.LoginDto;
import com.ssafy.enjoytrip.auth.dto.ReissueDto;
import com.ssafy.enjoytrip.auth.dto.TokenDto;
import com.ssafy.enjoytrip.auth.service.AuthService;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody ReissueDto reissueDto) {
        return ResponseEntity.ok(authService.reissue(reissueDto));
    }
}
