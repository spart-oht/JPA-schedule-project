package org.sparta.jpaschedule.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @NotNull(message = "유저 id는 필수 파라미터 입니다.")
    private Long id;

    @NotBlank(message = "유저명은 빈 문자열일 수 없습니다.")
    private String userName;

    @NotBlank(message = "이메일은 빈 문자열일 수 없습니다.")
    private String email;

}
