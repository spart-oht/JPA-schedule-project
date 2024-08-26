package org.sparta.jpaschedule.usersschedules.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.dto.CommonResponseDto;
import org.sparta.jpaschedule.usersschedules.dto.request.UsersSchedulesSaveDto;
import org.sparta.jpaschedule.usersschedules.dto.response.UsersSchedulesResponseDto;
import org.sparta.jpaschedule.usersschedules.entity.UsersScheduls;
import org.sparta.jpaschedule.usersschedules.service.UsersSchedulesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users_schedules")
@RequiredArgsConstructor
public class UsersSchedulesController {

    private final UsersSchedulesService usersSchedulesService;

    // 일정 담당자 등록
    @PostMapping("/setScheduleUser")
    public ResponseEntity<CommonResponseDto<UsersSchedulesResponseDto>> setScheduleUser(@Valid @RequestBody UsersSchedulesSaveDto usersSchedulesSaveDto){

        UsersScheduls usersSchedules = usersSchedulesService.usersSchedule(usersSchedulesSaveDto);

        UsersSchedulesResponseDto usersSchedulesResponseDto = UsersSchedulesResponseDto.builder()
                .id(usersSchedules.getId())
                .userName(usersSchedules.getUser().getUserName())
                .scheduleName(usersSchedules.getSchedule().getToDo())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", usersSchedulesResponseDto), HttpStatus.OK);
    }

}
