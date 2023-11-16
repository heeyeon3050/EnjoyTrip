package com.ssafy.enjoytrip.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.enjoytrip.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUserId(String userId);

	boolean existsByEmail(String email);

    Optional<User> findByUserId(String userId);

	@Override
	List<User> findAll();

	Optional<User> findByName(String name);
}
