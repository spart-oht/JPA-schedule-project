package org.sparta.jpaschedule.comment.service;

import org.sparta.jpaschedule.comment.dto.request.CommentDeleteDto;
import org.sparta.jpaschedule.comment.dto.request.CommentEditDto;
import org.sparta.jpaschedule.comment.dto.request.CommentUpdateDto;
import org.sparta.jpaschedule.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     *
     * @param commentEditDto
     * @return Comment
     */
    Comment editComment(CommentEditDto commentEditDto);


    /**
     *
     * @param id
     * @return Comment
     */
    Comment getComment(Long id);

    /**
     *
     * @param
     * @return List<Comment>
     */
    List<Comment> getComments();

    /**
     *
     * @param commentUpdateDto
     * @return Comment
     */
    Comment updateComment(CommentUpdateDto commentUpdateDto);

    /**
     *
     * @param commentDeleteDto
     * @return void
     */
    void deleteComment(CommentDeleteDto commentDeleteDto);


    /**
     *
     * @param id
     * @return Comment
     */
    Comment findComment(Long id);

}
