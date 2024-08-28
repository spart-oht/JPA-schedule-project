package org.sparta.jpaschedule.user.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.exception.CommonException;
import org.sparta.jpaschedule.common.exception.NotFoundException;
import org.sparta.jpaschedule.user.config.JwtAuth;
import org.sparta.jpaschedule.user.config.PasswordEncoder;
import org.sparta.jpaschedule.user.domain.UserRoleEnum;
import org.sparta.jpaschedule.user.dto.request.UserDeleteDto;
import org.sparta.jpaschedule.user.dto.request.UserLoginDto;
import org.sparta.jpaschedule.user.dto.request.UserSaveDto;
import org.sparta.jpaschedule.user.dto.request.UserUpdateDto;
import org.sparta.jpaschedule.user.domain.User;
import org.sparta.jpaschedule.user.exception.PasswordNotSameException;
import org.sparta.jpaschedule.user.repository.UserRepository;
import org.sparta.jpaschedule.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtAuth jwtAuth;

    @Override
    @Transactional
    public User saveUser(UserSaveDto userSaveDto, HttpServletResponse httpServletResponse) {
        try {

            String passwordEncode = passwordEncoder.encode(userSaveDto.getPassword());
            userSaveDto.setPassword(passwordEncode);

            User newUser = new User(userSaveDto);

            // 유저 id 로 토큰 발급 후 header 에 저장
            jwtAuth.createJwt(newUser, httpServletResponse);

            return userRepository.save(newUser);
        } catch (RuntimeException e){
            throw new RuntimeException("유저 등록 중 알수없는 오류가 발생하였습니다.");
        }

    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(UserUpdateDto userUpdateDto) {
        try {
            User user = findUser(userUpdateDto.getId());
            user.setUserName(userUpdateDto.getUserName());
            user.setEmail(userUpdateDto.getEmail());
            return user;
        } catch (RuntimeException e) {
            throw new RuntimeException("유저 수정 작업중 오류가 발생하였습니다.");
        }
    }

    @Override
    @Transactional
    public void deleteUser(UserDeleteDto userDeleteDto) {

        User user = findUser(userDeleteDto.getId());

        try{
            userRepository.delete(user);
        }catch (RuntimeException e){
            throw new RuntimeException("유저 삭제 작업중 오류가 발생하였습니다.");
        }


    }

    @Override
    public User loginCheck(UserLoginDto userLoginDto, HttpServletResponse httpServletResponse) {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(() -> new CommonException(HttpStatus.UNAUTHORIZED, "해당 이메일로 등록된 유저가 없습니다."));

        boolean checkPassword = passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());

        if(checkPassword){
            jwtAuth.createJwt(user, httpServletResponse);
        } else {
            // 비밀번호 미일치 exception
            throw new CommonException(HttpStatus.UNAUTHORIZED, "비밀번호를 확인해주세요.");
        }

        return user;
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 id 로 등록된 유저가 없습니다."));
    }

    @Override
    public boolean checkPermission(UserRoleEnum grade) {
        return grade.equals(UserRoleEnum.ADMIN);
    }

}
