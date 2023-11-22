package com.ssafy.enjoytrip.comment.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.enjoytrip.board.entity.Board;
import com.ssafy.enjoytrip.common.BaseEntity;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @JsonIgnore
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") // 데이터베이스의 FK 컬럼
    @JsonIgnore
    private Board board;

    public static Comment toComment(String content, User writer, Board board) {
        return Comment.builder()
                .content(content)
                .writer(writer)
                .board(board)
                .build();
    }

    @Builder
    private Comment(String content, User writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;
        board.getComments().add(this);
    }

    // public void update(CommentDto commentDto) {
    //     if (commentDto.getWriterId() != null) { this.writerId = commentDto.getWriterId(); }
    //     if (commentDto.getBoardId() != null) { this.boardId = commentDto.getBoardId(); }
    //     if (commentDto.getContent() != null) { this.content = commentDto.getContent(); }
    // }
}