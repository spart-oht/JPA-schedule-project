package org.sparta.jpaschedule.schedule.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEditDto {

    @NotNull(message = "작성유저명은 필수 입니다.")
    private Long userId;

    @NotNull(message = "할일 제목 파라미터는 필수입니다.")
    private String toDo;

    @NotNull(message = "할일 내용 파라미터는 필수입니다.")
    private String content;

}
