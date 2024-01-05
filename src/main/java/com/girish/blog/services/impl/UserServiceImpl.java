package com.girish.blog.services.impl;

import com.girish.blog.entities.User;
import com.girish.blog.payloads.UserDto;
import com.girish.blog.repositories.Userrepo;
import com.girish.blog.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.girish.blog.exceptions.ResourceNotFound;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements Userservice {
    @Autowired
    private Userrepo userp;


    @Override
    public UserDto createUser(UserDto userdto) {
        User user=this.DtotoUser(userdto);
        User savedUser=this.userp.save(user);
        return this.UsertoDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        User userOfId =this.userp.findById(userId)
                .orElseThrow(()->new ResourceNotFound("user","Id",userId));
        if(user.getName()!=null)
            userOfId.setName(user.getName());
        if(user.getEmail()!=null)
            userOfId.setEmail(user.getEmail());
        if(user.getPassword()!=null)
            userOfId.setPassword(user.getPassword());
        if(user.getAbout()!=null)
            userOfId.setAbout(user.getAbout());
        User updatedUser=this.userp.save(userOfId);
        return this.UsertoDto(updatedUser);


    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userp.findById(userId)
                .orElseThrow(()->new ResourceNotFound("User","Id",userId));

        return this.UsertoDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User>users=this.userp.findAll();
       List<UserDto>userdtos= users.stream().map(this::UsertoDto).collect(Collectors.toList());
        return userdtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userp.findById(userId)
                .orElseThrow(()->new ResourceNotFound("User","Id",userId));
        this.userp.delete(user);
    }


    private User DtotoUser(UserDto userDt){
        User user=new User();
        user.setId(userDt.getId());
        user.setName(userDt.getName());
        user.setEmail(userDt.getEmail());
        user.setPassword(userDt.getPassword());
        user.setAbout(userDt.getAbout());
        return user;

    }
    private UserDto UsertoDto(User useruser){
        UserDto user=new UserDto();
        user.setId(useruser.getId());
        user.setName(useruser.getName());
        user.setEmail(useruser.getEmail());
        user.setPassword(useruser.getPassword());
        user.setAbout(useruser.getAbout());
        return user;

    }
}
