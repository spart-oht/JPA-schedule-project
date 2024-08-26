package org.sparta.jpaschedule.schedule.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListDto {


    private int pageNum = 0;

    @Min(value = 1, message = "페이지 사이즈는 0보다 커야 합니다.")
    private int pageSize = 10;

    @Pattern(regexp = "^(updatedAt|createdAt|id)$", message = "유효하지 않는 정렬 기준입니다.")
    private String criteria = "updatedAt";

}
