package org.sparta.jpaschedule.schedule.service;

import org.sparta.jpaschedule.schedule.dto.request.ScheduleEditDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleUpdateDto;
import org.sparta.jpaschedule.schedule.entity.Schedule;

public interface ScheduleService {

    /**
     *
     * @param scheduleEditDto
     * @return scheduleResponseDto
     */
    Schedule editSchedule(ScheduleEditDto scheduleEditDto);


    /**
     *
     * @param id
     * @return scheduleResponseDto
     */
    Schedule getSchedule(Long id);

    /**
     *
     * @param scheduleUpdateDto
     * @return scheduleResponseDto
     */
    Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto);

}
