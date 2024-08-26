package org.sparta.jpaschedule.usersschedules.service;

import org.sparta.jpaschedule.usersschedules.dto.request.UsersSchedulesSaveDto;
import org.sparta.jpaschedule.usersschedules.entity.UsersScheduls;


public interface UsersSchedulesService {


    UsersScheduls usersSchedule(UsersSchedulesSaveDto usersSchedulesSaveDto);
}
