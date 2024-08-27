package org.sparta.jpaschedule.user.service;

import org.sparta.jpaschedule.user.dto.request.UserDeleteDto;
import org.sparta.jpaschedule.user.dto.request.UserSaveDto;
import org.sparta.jpaschedule.user.dto.request.UserUpdateDto;
import org.sparta.jpaschedule.user.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(UserSaveDto userSaveDto);

    User getUser(Long id);

    User findUser(Long id);

    List<User> getUsers();

    User updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(UserDeleteDto userDeleteDto);
}
