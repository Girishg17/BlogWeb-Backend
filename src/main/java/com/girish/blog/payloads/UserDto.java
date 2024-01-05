package com.girish.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min=4,message = "user name must be minimum 4 character")
    private String name;

    @Email(message = "Email address not valid")
    private String email;

    @NotEmpty
    @Size(min=4,max = 10,message = "password Should be minimum of 3 and maximum of 10 character")
    private String password;

    @NotEmpty
    private String about;

}
