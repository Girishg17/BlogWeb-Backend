package com.girish.blog.services;



import com.girish.blog.payloads.UserDto;

import java.util.List;

public interface Userservice {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);




}
