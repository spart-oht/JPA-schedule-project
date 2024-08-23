package org.sparta.jpaschedule.comment.repository;

import org.sparta.jpaschedule.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
