package org.sparta.jpaschedule.comment.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteDto {

    @NotNull(message = "삭제하실 id 값은 필수입니다.")
    private Long id;

}
