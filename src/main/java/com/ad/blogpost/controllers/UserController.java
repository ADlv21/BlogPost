package com.ad.blogpost.controllers;

import com.ad.blogpost.payloads.UserDto;
import com.ad.blogpost.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtoList = this.userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        UserDto userDto = this.userService.getUserById(Long.parseLong(userId));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // POST
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = this.userService.updateUser(userDto, Long.parseLong(userId));
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {

        this.userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);

    }
}
