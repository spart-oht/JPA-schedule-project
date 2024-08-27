package org.sparta.jpaschedule.comment.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sparta.jpaschedule.comment.dto.request.CommentDeleteDto;
import org.sparta.jpaschedule.comment.dto.request.CommentEditDto;
import org.sparta.jpaschedule.comment.dto.request.CommentUpdateDto;
import org.sparta.jpaschedule.comment.domain.Comment;
import org.sparta.jpaschedule.comment.repository.CommentRepository;
import org.sparta.jpaschedule.comment.service.CommentService;
import org.sparta.jpaschedule.common.exception.NotFoundException;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "commentService")
public class CommentServiceImpl implements CommentService {

    private final ScheduleService scheduleService;

    private final CommentRepository commentRepository;

    @Override
    public Comment editComment(CommentEditDto commentEditDto) {

        Comment newComment = new Comment(commentEditDto);
        newComment.setSchedule(scheduleService.findSchedule(commentEditDto.getScheduleId()));

        try{
            return commentRepository.save(newComment);
        } catch (RuntimeException e) {
            throw new RuntimeException("등록중 알수없는 에러가 발생하였습니다. 다시시도해 주십시오");
        }
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElse(new Comment());
    }

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public Comment updateComment(CommentUpdateDto commentUpdateDto) {
        Comment comment = findComment(commentUpdateDto.getId());
        try {
            comment.setComment(commentUpdateDto.getComment());
            return comment;
        } catch (RuntimeException e) {
            throw new RuntimeException("수정중 알수없는 에러가 발생하였습니다. 다시시도해 주십시오");
        }
    }

    @Override
    @Transactional
    public void deleteComment(CommentDeleteDto commentDeleteDto) {
        Comment comment = findComment(commentDeleteDto.getId());
        try {
            commentRepository.delete(comment);
        } catch (RuntimeException e) {
            throw new RuntimeException("삭제중 알수없는 에러가 발생하였습니다. 다시시도해 주십시오");
        }
    }

    @Override
    public Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 아이디로 등록된 데이터를 찾을 수 없습니다."));
    }
}
