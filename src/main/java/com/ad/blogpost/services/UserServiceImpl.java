package com.ad.blogpost.services;

import com.ad.blogpost.entities.Category;
import com.ad.blogpost.entities.User;
import com.ad.blogpost.exceptions.ResourceNotFoundException;
import com.ad.blogpost.payloads.UserDto;
import com.ad.blogpost.repositories.CategoryRepo;
import com.ad.blogpost.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }

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
        return userList.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        return userToDto(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepo.deleteById(userId);
    }

    @Override
    public List<UserDto> findAllUsersByName(String name) {

        List<User> userList = this.userRepo.findAllByName(name);
        return userList.stream().map(user -> userToDto(user)).collect(Collectors.toList());
    }

}
