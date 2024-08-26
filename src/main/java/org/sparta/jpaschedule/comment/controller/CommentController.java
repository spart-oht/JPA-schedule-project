package org.sparta.jpaschedule.comment.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.comment.dto.request.CommentDeleteDto;
import org.sparta.jpaschedule.comment.dto.request.CommentEditDto;
import org.sparta.jpaschedule.comment.dto.request.CommentUpdateDto;
import org.sparta.jpaschedule.comment.dto.response.CommentResponseDto;
import org.sparta.jpaschedule.comment.entity.Comment;
import org.sparta.jpaschedule.comment.service.CommentService;
import org.sparta.jpaschedule.common.dto.CommonResponseDto;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor()
public class CommentController {

    private final ScheduleService scheduleService;

    private final CommentService commentService;

    @PostMapping("/edit")
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> edit(@Valid @RequestBody CommentEditDto commentEditDto) {

        scheduleService.findSchedule(commentEditDto.getScheduleId());

        Comment newComment = commentService.editComment(commentEditDto);

        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .id(newComment.getId())
                .writer(newComment.getWriter())
                .comment(newComment.getComment())
                .createdAt(newComment.getCreatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", commentResponseDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> edit(@Valid @PathVariable(name = "id") Long id) {

        Comment getComment = commentService.getComment(id);

        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .id(getComment.getId())
                .writer(getComment.getWriter())
                .comment(getComment.getComment())
                .createdAt(getComment.getCreatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", commentResponseDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto<List<CommentResponseDto>>> edit() {

        List<Comment> getComments = commentService.getComments();

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for(Comment comment : getComments){
            commentResponseDtoList.add(
                    CommentResponseDto.builder()
                            .id(comment.getId())
                            .writer(comment.getWriter())
                            .comment(comment.getComment())
                            .createdAt(comment.getCreatedAt())
                            .build()
            );
        }

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", commentResponseDtoList), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> edit(@Valid @RequestBody CommentUpdateDto commentUpdateDto) {

        Comment updateComment = commentService.updateComment(commentUpdateDto);

        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .id(updateComment.getId())
                .writer(updateComment.getWriter())
                .comment(updateComment.getComment())
                .createdAt(updateComment.getUpdatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", commentResponseDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<CommonResponseDto<Void>> edit(@Valid @RequestBody CommentDeleteDto commentDeleteDto) {

        commentService.deleteComment(commentDeleteDto);

        return new ResponseEntity<>(new CommonResponseDto<>(200, "success", null), HttpStatus.OK);
    }
}
