package com.ssafy.enjoytrip.comment.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Where;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.comment.dto.CommentDto;
import com.ssafy.enjoytrip.common.BaseEntity;

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
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long writerId;
    private Long boardId;
    private String content;


    public static Comment toComment(CommentDto commentDto) {
        return Comment.builder()
                .writerId(commentDto.getWriterId())
                .boardId(commentDto.getBoardId())
                .content(commentDto.getContent())
                .build();
    }

    public void update(CommentDto commentDto) {
        if (commentDto.getWriterId() != null) { this.writerId = commentDto.getWriterId(); }
        if (commentDto.getBoardId() != null) { this.boardId = commentDto.getBoardId(); }
        if (commentDto.getContent() != null) { this.content = commentDto.getContent(); }
    }
}