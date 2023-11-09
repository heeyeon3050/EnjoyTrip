package com.ssafy.enjoytrip.comment.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long writerId;
    private Long boardId;
    private String content;
}
