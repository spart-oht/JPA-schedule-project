package org.sparta.jpaschedule.schedule.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sparta.jpaschedule.user.dto.response.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder

// jackson 라이브러리를 사용하는 경우
// dto 클래스를 사용하는 경우 null 인 값은 직렬화에서 제외함
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto {
    private Long id;

    private Long userId;

    private String toDo;

    private String content;

    private String weather;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<UserResponseDto> scheduleUsers;
}
