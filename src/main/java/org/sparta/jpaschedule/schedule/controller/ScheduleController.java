package org.sparta.jpaschedule.schedule.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.dto.CommonResponseDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleDeleteDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleEditDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleListDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleUpdateDto;
import org.sparta.jpaschedule.schedule.dto.response.ScheduleListResponseDto;
import org.sparta.jpaschedule.schedule.dto.response.ScheduleResponseDto;
import org.sparta.jpaschedule.schedule.entity.Schedule;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
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
                .writer(newSchedule.getWriter())
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

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(getSchedule.getId())
                .writer(getSchedule.getWriter())
                .toDo(getSchedule.getToDo())
                .content(getSchedule.getContent())
                .createdAt(getSchedule.getCreatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", scheduleResponseDto), HttpStatus.OK);
    }


    /**
     *
     * @param scheduleUpdateDto
     * @return scheduleResponseDto
     */
    @PostMapping("/update")
    public ResponseEntity<CommonResponseDto<ScheduleResponseDto>> updateSchedule(@Valid @RequestBody ScheduleUpdateDto scheduleUpdateDto) {

        Schedule getSchedule = scheduleService.updateSchedule(scheduleUpdateDto);

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
                            .writer(schedule.getWriter())
                            .build()
            );
        }

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", scheduleResponseList), HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponseDto<Void>> deleteSchedule(@Valid @RequestBody ScheduleDeleteDto scheduleDeleteDto){

        scheduleService.deleteSchedule(scheduleDeleteDto);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", null), HttpStatus.OK);
    }

}
