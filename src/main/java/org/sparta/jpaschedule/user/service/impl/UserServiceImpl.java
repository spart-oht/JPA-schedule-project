package org.sparta.jpaschedule.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sparta.jpaschedule.common.exception.NotFoundException;
import org.sparta.jpaschedule.user.dto.request.UserDeleteDto;
import org.sparta.jpaschedule.user.dto.request.UserSaveDto;
import org.sparta.jpaschedule.user.dto.request.UserUpdateDto;
import org.sparta.jpaschedule.user.entity.User;
import org.sparta.jpaschedule.user.repository.UserRepository;
import org.sparta.jpaschedule.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(UserSaveDto userSaveDto) {
        try {
            return userRepository.save(new User(userSaveDto));
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
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 id 로 등록된 유저가 없습니다."));
    }

}
