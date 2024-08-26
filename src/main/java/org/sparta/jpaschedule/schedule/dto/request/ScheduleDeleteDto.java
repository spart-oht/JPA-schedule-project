package org.sparta.jpaschedule.schedule.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDeleteDto {

    @NotNull(message = "id 는 필수 파라미터 입니다.")
    private Long id;

}
