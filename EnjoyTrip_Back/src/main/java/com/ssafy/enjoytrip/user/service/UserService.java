package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.auth.entity.Authority;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import com.ssafy.enjoytrip.image.entity.Image;
import com.ssafy.enjoytrip.image.service.ImageService;
import com.ssafy.enjoytrip.user.dto.UpdateDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.dto.UserResponseDto;
import com.ssafy.enjoytrip.user.entity.User;
import com.ssafy.enjoytrip.user.exception.PasswordNotEqualException;
import com.ssafy.enjoytrip.user.exception.UserExistException;
import com.ssafy.enjoytrip.user.exception.UserNotFoundException;
import com.ssafy.enjoytrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ImageService imageService;

	@Transactional
	public CommonResponse join(UserDto userDto) {
		if (userRepository.existsByUserId(userDto.getUserId())) {
			throw new UserExistException("이미 가입되어 있는 유저입니다.");
		}

		if (userRepository.existsByName(userDto.getName())) {
			throw new UserExistException("이미 가입되어 있는 닉네임입니다.");
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserExistException("이미 가입되어 있는 이메일입니다.");
		}

		User user = User.toUser(userDto, Authority.USER, passwordEncoder);
		return new CommonResponse(true, "Success to create user", UserResponseDto.toUserResponseDto(userRepository.save(user)));
	}

	public CommonResponse getById(String userId) {
		return new CommonResponse(true, "Success to get User.", UserResponseDto.toUserResponseDto(userRepository.findByUserId(userId).orElse(null)));
	}

	@Transactional
	public CommonResponse update(String userId, UpdateDto updateDto, User user) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);

		if (!optionalUser.isPresent())
			throw new UserNotFoundException(String.format("사용자(%s)를 찾을 수 없습니다.", userId));

		if(updateDto.getPassword_old() != null) {
			if (!updateDto.getPassword_old().equals(user.getPassword()))
				throw new PasswordNotEqualException("비밀번호가 불일치합니다.");
		}

		User existUser = optionalUser.get();
		existUser.update(updateDto, passwordEncoder);
		userRepository.save(existUser);

		return new CommonResponse(true, "Success to update User.", UserResponseDto.toUserResponseDto(existUser));
	}

	@Transactional
	public CommonResponse delete(String userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);

		if (optionalUser.isPresent()) {
			User existUser = optionalUser.get();
			existUser.delete();
			userRepository.save(existUser);
			return new CommonResponse(true, "Success to delete User.", existUser);
		}
		throw new UserNotFoundException(String.format("사용자(%s)를 찾을 수 없습니다.", userId));
	}

	public CommonResponse getList() {
		return new CommonResponse(true, "Success to user list.", userRepository.findAll());
	}

	public Optional<User> findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	public CommonResponse checkIdDuplication(String userId) {
		return new CommonResponse(true, "Success to check userId.", userRepository.existsByUserId(userId));
	}

	public CommonResponse checkNameDuplication(String name) {
		return new CommonResponse(true, "Success to check name.", userRepository.existsByName(name));
	}

	public CommonResponse checkEmailDuplication(String email) {
		return new CommonResponse(true, "Success to check email.", userRepository.existsByEmail(email));
	}

	@Transactional
	public CommonResponse upload(MultipartFile image, UpdateDto updateDto, User user) throws IOException {
		imageService.upload(image);
		user.update(updateDto, passwordEncoder);
		return new CommonResponse(true, "Success to create board", userRepository.save(user));
	}
}