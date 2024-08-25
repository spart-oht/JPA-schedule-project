package org.sparta.jpaschedule.schedule.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateDto {

    private Long id;

    private String toDo;

    private String content;

}
