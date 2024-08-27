package org.sparta.jpaschedule;


import org.junit.jupiter.api.Test;
import org.sparta.jpaschedule.schedule.domain.Schedule;
import org.sparta.jpaschedule.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    void testSchedule() {

        // 지연 로딩 테스트
        Schedule schedule = scheduleRepository.findById(4L).orElse(null);

    }
}
