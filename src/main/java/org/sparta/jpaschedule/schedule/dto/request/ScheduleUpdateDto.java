package org.sparta.jpaschedule.schedule.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateDto {

    @NotNull(message = "수정 시 id 파라미터는 필수입니다.")
    private Long id;

    @NotNull(message = "수정 시 할일 제목 파라미터는 필수입니다.")
    private String toDo;


    @NotNull(message = "수정 시 할일 내용 파라미터는 필수입니다.")
    private String content;

}
