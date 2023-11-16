package com.ssafy.enjoytrip.common.rq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.ssafy.enjoytrip.user.entity.User;
import com.ssafy.enjoytrip.user.service.UserService;

@Component
@RequestScope
public class Rq {
	private final UserService userService;
	private final HttpServletRequest req;
	private final HttpServletResponse resp;
	private final HttpSession session;
	private User user;

	public Rq(UserService userService, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		this.userService = userService;
		this.req = req;
		this.resp = resp;
		this.session = session;

		// 현재 로그인한 회원의 인증정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getPrincipal() instanceof User) {
			this.user = (User) authentication.getPrincipal();
		} else {
			this.user = null;
		}
	}

	// 로그인 되어 있는지 체크
	public boolean isLogin() {
		return user != null;
	}

	// 로그아웃 되어 있는지 체크
	public boolean isLogout() {
		return !isLogin();
	}

	// 로그인 된 회원의 객체
	public User getUser() {
		if (isLogout()) return null;

		// 데이터가 없는지 체크
		if (user == null) {
			user = userService.findByName(user.getName()).orElseThrow();
		}

		return user;
	}
}
