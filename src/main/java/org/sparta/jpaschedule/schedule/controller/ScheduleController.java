package org.sparta.jpaschedule.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.dto.CommonResponseDto;
import org.sparta.jpaschedule.schedule.dto.request.*;
import org.sparta.jpaschedule.schedule.dto.response.ScheduleListResponseDto;
import org.sparta.jpaschedule.schedule.dto.response.ScheduleResponseDto;
import org.sparta.jpaschedule.schedule.domain.Schedule;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
import org.sparta.jpaschedule.user.dto.response.UserResponseDto;
import org.sparta.jpaschedule.usersschedules.entity.UsersScheduls;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     *
     * @param scheduleEditDto
     * @return scheduleResponseDto
     */
    @PostMapping("/edit")
    public ResponseEntity<CommonResponseDto<ScheduleResponseDto>> editSchedule(@Valid @RequestBody ScheduleEditDto scheduleEditDto){

        Schedule newSchedule = scheduleService.editSchedule(scheduleEditDto);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(newSchedule.getId())
                .userId(newSchedule.getUser().getId())
                .toDo(newSchedule.getToDo())
                .content(newSchedule.getContent())
                .createdAt(newSchedule.getCreatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", scheduleResponseDto), HttpStatus.OK);
    }


    /**
     *
     * @param id
     * @return scheduleResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<ScheduleResponseDto>> getSchedule(@Valid @PathVariable(value = "id") Long id) {

        Schedule getSchedule = scheduleService.getSchedule(id);

        List<UserResponseDto> scheduleUsers = new ArrayList<>();

        for(UsersScheduls userSchedule : getSchedule.getUsersScheduls()){
            scheduleUsers.add(
                    UserResponseDto.builder()
                            .id(userSchedule.getUser().getId())
                            .userName(userSchedule.getUser().getUserName())
                            .email(userSchedule.getUser().getEmail())
                            .build()
            );

        }

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(getSchedule.getId())
                .userId(getSchedule.getUser().getId())
                .toDo(getSchedule.getToDo())
                .content(getSchedule.getContent())
                .createdAt(getSchedule.getCreatedAt())
                .scheduleUsers(scheduleUsers)
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", scheduleResponseDto), HttpStatus.OK);
    }


    /**
     *
     * @param scheduleUpdateDto
     * @return scheduleResponseDto
     */
    @PutMapping("/update")
    public ResponseEntity<CommonResponseDto<ScheduleResponseDto>> updateSchedule(@Valid @RequestBody ScheduleUpdateDto scheduleUpdateDto, HttpServletRequest request) {

        Schedule getSchedule = scheduleService.updateSchedule(scheduleUpdateDto, request);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .toDo(getSchedule.getToDo())
                .content(getSchedule.getContent())
                .updatedAt(getSchedule.getUpdatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", scheduleResponseDto), HttpStatus.OK);
    }

    /**
     *
     * @param scheduleListDto
     * @return scheduleResponseDto
     */
    @GetMapping
    public ResponseEntity<CommonResponseDto<List<ScheduleListResponseDto>>> getSchedules(@Valid @ModelAttribute ScheduleListDto scheduleListDto) {

        Page<Schedule> getSchedules = scheduleService.getSchedules(scheduleListDto);

        List<ScheduleListResponseDto> scheduleResponseList = new ArrayList<>();

        for(Schedule schedule : getSchedules){
            scheduleResponseList.add(
                    ScheduleListResponseDto.builder()
                            .toDo(schedule.getToDo())
                            .content(schedule.getContent())
                            .commentCount(schedule.getComments().size())
                            .createdAt(schedule.getCreatedAt())
                            .updatedAt(schedule.getUpdatedAt())
                            .userId(schedule.getUser().getId())
                            .build()
            );
        }

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", scheduleResponseList), HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponseDto<Void>> deleteSchedule(@Valid @RequestBody ScheduleDeleteDto scheduleDeleteDto, HttpServletRequest request){

        scheduleService.deleteSchedule(scheduleDeleteDto, request);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", null), HttpStatus.OK);
    }
}
