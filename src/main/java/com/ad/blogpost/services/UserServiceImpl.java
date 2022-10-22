package com.ad.blogpost.services;

import com.ad.blogpost.entities.User;
import com.ad.blogpost.exceptions.ResourceNotFoundException;
import com.ad.blogpost.payloads.UserDto;
import com.ad.blogpost.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        User savedUser = this.userRepo.save(dtoToUser(user));
        return userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = this.userRepo.findAll();

        //List<UserDto> userDto = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(userToDto(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User usUpdated = userRepo.save(user);

        return userToDto(usUpdated);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }
}
