package org.sparta.jpaschedule.schedule.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEditDto {

    private String writer;

    private String toDo;

    private String content;

}
