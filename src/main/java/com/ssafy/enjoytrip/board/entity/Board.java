package com.ssafy.enjoytrip.board.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.comment.entity.Comment;
import com.ssafy.enjoytrip.common.BaseEntity;
import com.ssafy.enjoytrip.image.entity.Image;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User writer;
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Comment> comments;
	private String title;
	private String content;
	@Enumerated(EnumType.STRING)
	private BoardCategory category;
	private Double latitude;
	private Double longitude;
	@ManyToMany
	@JsonManagedReference
	@JoinTable(
		name = "like_board",
		joinColumns = @JoinColumn(name = "board_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> board_users = new HashSet<>();
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Image> images;

	// public static Board toBoard(BoardDto boardDto, User writer, List<Image> images) {
	// 	return Board.builder()
	// 		.title(boardDto.getTitle())
	// 		.writer(writer)
	// 		.content(boardDto.getContent())
	// 		.category(boardDto.getCategory())
	// 		.latitude(boardDto.getLatitude())
	// 		.longitude(boardDto.getLongitude())
	// 		.images(images)
	// 		.build();
	// }
	public static Board toBoard(BoardDto boardDto, User writer) {
		return Board.builder()
			.title(boardDto.getTitle())
			.writer(writer)
			.content(boardDto.getContent())
			.category(boardDto.getCategory())
			.latitude(boardDto.getLatitude())
			.longitude(boardDto.getLongitude())
			.build();
	}

	public void update(BoardDto boardDto) {
		if (boardDto.getTitle() != null) { this.title = boardDto.getTitle(); }
		if (boardDto.getContent() != null) { this.content = boardDto.getContent(); }
		if (boardDto.getCategory() != null) { this.category = boardDto.getCategory(); }
		if (boardDto.getLatitude() != null) { this.latitude = boardDto.getLatitude(); }
		if (boardDto.getLongitude() != null) { this.longitude = boardDto.getLongitude(); }
	}
}
