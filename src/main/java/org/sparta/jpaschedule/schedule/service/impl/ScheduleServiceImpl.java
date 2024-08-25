package org.sparta.jpaschedule.schedule.service.impl;


import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.exception.NotFoundException;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleEditDto;
import org.sparta.jpaschedule.schedule.dto.request.ScheduleUpdateDto;
import org.sparta.jpaschedule.schedule.entity.Schedule;
import org.sparta.jpaschedule.schedule.repository.ScheduleRepository;
import org.sparta.jpaschedule.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public Schedule editSchedule(ScheduleEditDto scheduleEditDto) {

        Schedule schedule = new Schedule(scheduleEditDto);

        try {
            return scheduleRepository.save(schedule);
        } catch (RuntimeException e){
            throw new RuntimeException("저장이 실패되었습니다.");
        }
    }

    @Override
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id).orElse(new Schedule());
    }

    @Override
    @Transactional
    public Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto) {
        Schedule findSchedule = this.findSchedule(scheduleUpdateDto.getId());

        try {
            findSchedule.setToDo(scheduleUpdateDto.getToDo());
            findSchedule.setContent(scheduleUpdateDto.getContent());

            return findSchedule;

        } catch (RuntimeException e){
            throw new RuntimeException("수정 중 알수없는 에러가 발생하였습니다.");
        }


    }

    private Schedule findSchedule(Long id){
        return scheduleRepository.findById(id).orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다."));
    }
}
