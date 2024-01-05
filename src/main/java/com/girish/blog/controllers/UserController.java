package com.girish.blog.controllers;

import com.girish.blog.entities.User;
import com.girish.blog.payloads.UserDto;
import com.girish.blog.services.Userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private Userservice userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> CreateUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> UpdateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
        UserDto UpdatedUser = this.userService.updateUser(userDto, uid);
        return new ResponseEntity<>(UpdatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid) {
        this.userService.deleteUser(uid);
        return new ResponseEntity<>(Map.of("message", "user deleted succesfully"), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @PostMapping("/getById/{userId}")
    public ResponseEntity<UserDto> getByUserId(@PathVariable("userId") Integer uid)
    {
        return ResponseEntity.ok(this.userService.getUserById(uid));
    }




}
