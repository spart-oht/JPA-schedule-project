package org.sparta.jpaschedule.usersschedules.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersSchedulesSaveDto {

    @NotNull(message = "user id 는 필수 파라미터 입니다.")
    private Long userId;

    @NotNull(message = "schedule id 는 필수 파라미터 입니다.")
    private Long scheduleId;



}
