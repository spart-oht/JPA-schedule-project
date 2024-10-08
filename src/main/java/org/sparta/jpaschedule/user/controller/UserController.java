package org.sparta.jpaschedule.user.controller;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.dto.CommonResponseDto;
import org.sparta.jpaschedule.common.config.JwtAuth;
import org.sparta.jpaschedule.user.dto.request.UserDeleteDto;
import org.sparta.jpaschedule.user.dto.request.UserLoginDto;
import org.sparta.jpaschedule.user.dto.request.UserSaveDto;
import org.sparta.jpaschedule.user.dto.request.UserUpdateDto;
import org.sparta.jpaschedule.user.dto.response.UserResponseDto;
import org.sparta.jpaschedule.user.domain.User;
import org.sparta.jpaschedule.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtAuth jwtAuth;

    @PostMapping("/edit")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> saveUser(@Valid @RequestBody UserSaveDto userSaveDto, HttpServletResponse httpServletResponse) {

        User newUser = userService.saveUser(userSaveDto, httpServletResponse);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(newUser.getId())
                .userName(newUser.getUserName())
                .email(newUser.getEmail())
                .createdAt(newUser.getCreatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", userResponseDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> getUser(@PathVariable Long id) {

        User user = userService.getUser(id);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", userResponseDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto<List<UserResponseDto>>> getUsers() {
        List<User> users = userService.getUsers();

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user : users){
            userResponseDtoList.add(
                    UserResponseDto.builder()
                            .id(user.getId())
                            .userName(user.getUserName())
                            .email(user.getEmail())
                            .createdAt(user.getCreatedAt())
                            .build()
            );
        }

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", userResponseDtoList), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        User updateUser = userService.updateUser(userUpdateDto);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(updateUser.getId())
                .userName(updateUser.getUserName())
                .email(updateUser.getEmail())
                .updatedAt(updateUser.getUpdatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", userResponseDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponseDto<Void>> deleteUser(UserDeleteDto userDeleteDto) {

        userService.deleteUser(userDeleteDto);

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", null), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto<UserResponseDto>> loginCheck(@Valid @RequestBody UserLoginDto userLoginDto, HttpServletResponse httpServletResponse){

        User user = userService.loginCheck(userLoginDto, httpServletResponse);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .grade(user.getGrade())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", userResponseDto), HttpStatus.OK);
    }

}
