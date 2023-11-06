package com.ssafy.enjoytrip.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.enjoytrip.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUserId(String userId);

	boolean existsByEmail(String email);
}
