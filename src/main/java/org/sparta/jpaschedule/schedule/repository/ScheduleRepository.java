package org.sparta.jpaschedule.schedule.repository;

import org.sparta.jpaschedule.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
