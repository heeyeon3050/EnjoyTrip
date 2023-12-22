package com.ssafy.enjoytrip.auth.service;

import com.ssafy.enjoytrip.auth.dto.LoginDto;
import com.ssafy.enjoytrip.auth.config.jwt.JwtTokenProvider;
import com.ssafy.enjoytrip.auth.dto.ReissueDto;
import com.ssafy.enjoytrip.auth.dto.TokenDto;
import com.ssafy.enjoytrip.auth.exeption.AccessTokenNotValidException;
import com.ssafy.enjoytrip.auth.exeption.RefreshTokenNotValidException;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.common.exception.BadRequestException;
import com.ssafy.enjoytrip.user.entity.User;
import com.ssafy.enjoytrip.user.exception.UserNotFoundException;
import com.ssafy.enjoytrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final RedisTemplate redisTemplate;

	@Transactional
	public CommonResponse login(LoginDto loginDto) {
		Optional<User> optionalUser = userRepository.findByUserId(loginDto.getUserId());

		if (optionalUser.isPresent()) {
			User existUser = optionalUser.get();
			UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication(); // ID/PW로 AuthenticationToken 생성
			Authentication authentication = authenticationManagerBuilder.getObject()
				.authenticate(
					authenticationToken); // 사용자 비밀번호 체크, CustomUserDetailsService에서의 loadUserByUsername 메서드가 실행됨
			SecurityContextHolder.getContext().setAuthentication(authentication);//securityContext에 저장

			TokenDto tokenDto = jwtTokenProvider.createToken(authentication);

			redisTemplate.opsForValue()
				.set("RT: " + authentication.getName(), tokenDto.getRefreshToken(),
					tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

			tokenDto.updateUserId(existUser.getUserId());

			return new CommonResponse(true, "Success to login.", tokenDto);
		}
		throw new UserNotFoundException(String.format("아이디(%s) 로 가입된 사용자를 찾을 수 없습니다.", loginDto.getUserId()));

	}

	@Transactional
	public TokenDto reissue(ReissueDto reissueDto) {

		// 1. Refresh Token 검증
		if (!jwtTokenProvider.validateToken(reissueDto.getRefreshToken())) {
			throw new RefreshTokenNotValidException("Refresh Token 이 유효하지 않습니다.");
		}

		// 2. Access Token 에서 User ID 가져오기
		Authentication authentication = jwtTokenProvider.getAuthentication(reissueDto.getAccessToken());

		// 3. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져옴
		String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());

		// 로그아웃되어 Redis에 RefreshToken이 존재하지 않는 경우처리
		if (ObjectUtils.isEmpty(refreshToken)) {
			throw new BadRequestException("잘못된 요청입니다");
		}

		// 4. Refresh Token 일치하는지 검사
		if (!refreshToken.equals(reissueDto.getRefreshToken())) {
			throw new AccessTokenNotValidException("토큰의 유저 정보가 일치하지 않습니다.");
		}

		// 5. 새로운 토큰 생성
		TokenDto newTokenDto = jwtTokenProvider.createToken(authentication);

		// 6. 저장소 정보 업데이트
		redisTemplate.opsForValue()
			.set("RT: " + authentication.getName(), newTokenDto.getRefreshToken(),
				newTokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

		// 토큰 발급
		return newTokenDto;
	}

}
