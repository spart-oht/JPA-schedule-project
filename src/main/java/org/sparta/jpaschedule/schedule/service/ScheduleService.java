package org.sparta.jpaschedule.schedule.service;

import jakarta.servlet.http.HttpServletRequest;
import org.sparta.jpaschedule.schedule.dto.request.*;
import org.sparta.jpaschedule.schedule.domain.Schedule;
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
    Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto, HttpServletRequest request);

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


    void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto, HttpServletRequest request);


}
