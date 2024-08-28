package org.sparta.jpaschedule.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @Email
    @NotNull(message = "이메일은 필수 파라미터 입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수 파라미터 입니다.")
    private String password;

}
