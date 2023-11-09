package com.ssafy.enjoytrip.comment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.attraction.exception.AttractionNotFoundException;
import com.ssafy.enjoytrip.attraction.repository.AttractionRepository;
import com.ssafy.enjoytrip.comment.dto.CommentDto;
import com.ssafy.enjoytrip.comment.entity.Comment;
import com.ssafy.enjoytrip.comment.repository.CommentRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CommonResponse create(CommentDto commentDto) {
        Comment comment = Comment.toComment(commentDto);
        return new CommonResponse(true, "Success to create comment", commentRepository.save(comment));
    }

    public CommonResponse getByWriterId(Long writerId) {
        return new CommonResponse(true, "Success to get comment.", commentRepository.findAllByWriterId(
            writerId));
    }

    public CommonResponse getByBoardId(Long boardId) {
        return new CommonResponse(true, "Success to get comment.", commentRepository.findAllByBoardId(
            boardId));
    }

    @Transactional
    public CommonResponse update(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(optionalComment.isPresent()) {
            Comment existComment = optionalComment.get();
            existComment.update(commentDto);
            return new CommonResponse(true, "Success to update comment.", commentRepository.save(existComment));
        }
        throw new AttractionNotFoundException(String.format("댓글(%s)를 찾을 수 없습니다.", id));
    }

    @Transactional
    public CommonResponse delete(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(optionalComment.isPresent()) {
            Comment existComment = optionalComment.get();
            existComment.delete();
            return new CommonResponse(true, "Success to delete comment.", commentRepository.save(existComment));
        }
        throw new AttractionNotFoundException(String.format("댓글(%s)를 찾을 수 없습니다.", id));
    }
}
