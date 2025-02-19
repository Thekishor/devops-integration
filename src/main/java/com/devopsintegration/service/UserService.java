package com.devopsintegration.service;

import com.devopsintegration.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto userDto);

    UserDto getUserById(String id);

    List<UserDto> getAllUser();

    void deleteUser(String id);

    void updateUser(String id, UserDto userDto);
}
