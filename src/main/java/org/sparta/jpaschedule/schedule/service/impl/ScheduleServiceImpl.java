package org.sparta.jpaschedule.schedule.service.impl;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sparta.jpaschedule.common.exception.CommonException;
import org.sparta.jpaschedule.common.exception.NotFoundException;
import org.sparta.jpaschedule.schedule.dto.request.*;
import org.sparta.jpaschedule.schedule.domain.Schedule;
import org.sparta.jpaschedule.schedule.repository.ScheduleRepository;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
import org.sparta.jpaschedule.user.domain.User;
import org.sparta.jpaschedule.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    private final UserService userService;

    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public Schedule editSchedule(ScheduleEditDto scheduleEditDto) {

        User user = userService.findUser(scheduleEditDto.getUserId());

        try {
            Schedule schedule = new Schedule(scheduleEditDto);
            schedule.setUser(user);

            return scheduleRepository.save(schedule);
        } catch (RuntimeException e){
            throw new RuntimeException("저장이 실패되었습니다.");
        }
    }

    @Override
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto, HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        if(userService.checkPermission(user.getGrade())) throw new CommonException(HttpStatus.FORBIDDEN ,"일정 삭제는 관리자 권한이 있는 유저만 가능합니다.");

        Schedule findSchedule = findSchedule(scheduleUpdateDto.getId());

        try {

            findSchedule.setToDo(scheduleUpdateDto.getToDo());
            findSchedule.setContent(scheduleUpdateDto.getContent());

            return findSchedule;

        } catch (RuntimeException e){
            throw new RuntimeException("수정 중 알수없는 에러가 발생하였습니다.");
        }

    }

    @Override
    public Schedule findSchedule(Long id){
        return scheduleRepository.findById(id).orElseThrow(() -> new NotFoundException("일정 데이터가 존재하지 않습니다."));
    }

    @Override
    public Page<Schedule> getSchedules(ScheduleListDto scheduleListDto) {
        Pageable pageable = PageRequest.of(
                scheduleListDto.getPageNum(),
                scheduleListDto.getPageSize(),
                Sort.by(Sort.Direction.DESC, scheduleListDto.getCriteria())
        );

        return scheduleRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto, HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        if(userService.checkPermission(user.getGrade())) throw new CommonException(HttpStatus.FORBIDDEN ,"일정 삭제는 관리자 권한이 있는 유저만 가능합니다.");

        Schedule schedule = findSchedule(scheduleDeleteDto.getId());

        try {
            scheduleRepository.delete(schedule);
        } catch (RuntimeException e){
            throw new RuntimeException("삭제중 알수없는 에러가 발생하였습니다.");
        }


    }

}
