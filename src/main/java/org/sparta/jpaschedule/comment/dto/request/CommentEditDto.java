package org.sparta.jpaschedule.comment.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditDto {

    @NotNull(message = "작성유저명은 필수 입니다.")
    private String writer;

    @NotNull(message = "댓글 내용 파라미터는 필수입니다.")
    private String content;

    @NotNull(message = "일정 id 파라미터는 필수입니다.")
    private Long scheduleId;

}
