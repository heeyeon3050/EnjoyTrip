package com.ssafy.enjoytrip.board.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.board.entity.BoardCategory;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable {
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private Double latitude;
	private Double longitude;

	private String imageUrl;
	private Set<Long> userIds;
}