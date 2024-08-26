package org.sparta.jpaschedule.comment.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {

    @NotNull(message = "수정 시 id 파라미터는 필수입니다.")
    private Long id;

    @NotNull(message = "수정 시 댓글 내용 파라미터는 필수입니다.")
    private String comment;

}
