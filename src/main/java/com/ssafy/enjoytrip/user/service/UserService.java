package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.auth.entity.Authority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.user.dto.RegistDto;
import com.ssafy.enjoytrip.user.entity.User;
import com.ssafy.enjoytrip.user.exception.UserExistException;
import com.ssafy.enjoytrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public CommonResponse regist(RegistDto registDto) {
		if (userRepository.existsByUserId(registDto.getUserId())) {
			throw new UserExistException("이미 가입되어 있는 유저입니다");
		}

		if (userRepository.existsByEmail(registDto.getEmail())) {
			throw new UserExistException("이미 가입되어 있는 유저입니다");
		}

		User user = User.toUser(registDto, Authority.ROLE_USER, passwordEncoder);

		return new CommonResponse(true, "Success to create user", userRepository.save(user));
	}
}
