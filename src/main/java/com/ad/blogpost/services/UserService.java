package com.ad.blogpost.services;

import com.ad.blogpost.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user, Long userId);

    void deleteUser(Long userId);

}
