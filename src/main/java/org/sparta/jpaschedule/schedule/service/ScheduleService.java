package org.sparta.jpaschedule.schedule.service;

import org.sparta.jpaschedule.schedule.dto.request.ScheduleDeleteDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleEditDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleListDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleUpdateDto;
import org.sparta.jpaschedule.schedule.entity.Schedule;
import org.springframework.data.domain.Page;

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

    /**
     *
     * @param id
     * @return scheduleResponseDto
     */
    Schedule findSchedule(Long id);

    /**
     *
     * @param scheduleListDto
     * @return scheduleResponseDto
     */
    Page<Schedule> getSchedules(ScheduleListDto scheduleListDto);


    void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);
}
