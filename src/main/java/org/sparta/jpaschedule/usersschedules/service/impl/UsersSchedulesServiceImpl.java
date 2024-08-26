package org.sparta.jpaschedule.usersschedules.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.exception.NotFoundException;
import org.sparta.jpaschedule.schedule.entity.Schedule;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
import org.sparta.jpaschedule.user.entity.User;
import org.sparta.jpaschedule.user.service.UserService;
import org.sparta.jpaschedule.usersschedules.dto.request.UsersSchedulesSaveDto;
import org.sparta.jpaschedule.usersschedules.entity.UsersScheduls;
import org.sparta.jpaschedule.usersschedules.repository.UsersSchedulesRepository;
import org.sparta.jpaschedule.usersschedules.service.UsersSchedulesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersSchedulesServiceImpl implements UsersSchedulesService {

    private final UserService userService;

    private final ScheduleService scheduleService;

    private final UsersSchedulesRepository usersSchedulesRepository;

    @Override
    public UsersScheduls usersSchedule(UsersSchedulesSaveDto usersSchedulesSaveDto) {

        Schedule schedule = scheduleService.findSchedule(usersSchedulesSaveDto.getScheduleId());

        User user = userService.findUser(usersSchedulesSaveDto.getUserId());

        try {

            UsersScheduls usersSchedules = UsersScheduls.builder()
                    .user(user)
                    .schedule(schedule)
                    .build();

            return usersSchedulesRepository.save(usersSchedules);

        } catch (RuntimeException e){
            throw new RuntimeException("저장이 실패되었습니다.");
        }
    }

}
