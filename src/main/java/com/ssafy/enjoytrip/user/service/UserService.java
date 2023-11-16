package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.attraction.exception.AttractionNotFoundException;
import com.ssafy.enjoytrip.auth.entity.Authority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.entity.User;
import com.ssafy.enjoytrip.user.exception.UserExistException;
import com.ssafy.enjoytrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public CommonResponse join(UserDto userDto) { //signin regist 없다며 뭘로 하지
		if (userRepository.existsByUserId(userDto.getUserId())) {
			throw new UserExistException("이미 있는 아이디입니다");
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserExistException("이미 있는 이메일입니다");
		}

		User user = User.toUser(userDto, Authority.USER, passwordEncoder);

		return new CommonResponse(true, "Success to create user", userRepository.save(user));
	}

	public CommonResponse getById(String userId) {
		return new CommonResponse(true, "Success to get User.", userRepository.existsByUserId(userId));
	}

	public CommonResponse idCheck(String userId) {
		return new CommonResponse(true, "Success to check userId.", userRepository.existsByUserId(userId));
	}

	@Transactional
	public CommonResponse update(String userId, UserDto userDto) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);

		if(optionalUser.isPresent()) {
			User existUser = optionalUser.get();
			existUser.update(userDto);
			userRepository.save(existUser);
			return new CommonResponse(true, "Success to update User.", existUser);
		}
		throw new AttractionNotFoundException(String.format("사용자(%s)를 찾을 수 없습니다.", userId));
	}

	@Transactional
	public CommonResponse delete(String userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);

		if(optionalUser.isPresent()) {
			User existUser = optionalUser.get();
			existUser.delete();
			userRepository.save(existUser);
			return new CommonResponse(true, "Success to delete User.", existUser);
		}
		throw new AttractionNotFoundException(String.format("관광지(%s)를 찾을 수 없습니다.", userId));
	}
	public CommonResponse getList() {
		return new CommonResponse(true, "Success to user list.", userRepository.findAll());
	}

	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}
}
