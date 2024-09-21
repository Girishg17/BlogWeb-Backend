package com.girish.blog;

import com.girish.blog.controllers.UserController;
import com.girish.blog.payloads.UserDto;
import com.girish.blog.services.Userservice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)

class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;
    //help to test the controller

    @MockBean
    private Userservice userService;

    // Test for creating a user
    @Test
    void testCreateUser() throws Exception {
        // Mock UserDto
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("84923");
        userDto.setAbout("luchbi");

        // Mock the service call
        Mockito.when(userService.createUser(any(UserDto.class))).thenReturn(userDto);
          //mockito for creating object
        // Perform POST request
        mockMvc.perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\", \"password\":\"84923\", \"about\":\"luchbi\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"84923\",\"about\":\"luchbi\"}"));
    }

    // Test for updating a user
    @Test
    void testUpdateUser() throws Exception {
        // Mock updated UserDto
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(1);
        updatedUserDto.setName("John Doe Updated");
        updatedUserDto.setEmail("john.doe.updated@example.com");
        updatedUserDto.setPassword("newpass");
        updatedUserDto.setAbout("Updated bio");

        // Mock the service call
        Mockito.when(userService.updateUser(any(UserDto.class), eq(1))).thenReturn(updatedUserDto);

        // Perform PUT request
        mockMvc.perform(put("/api/users/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe Updated\", \"email\":\"john.doe.updated@example.com\", \"password\":\"newpass\", \"about\":\"Updated bio\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"John Doe Updated\",\"email\":\"john.doe.updated@example.com\",\"password\":\"newpass\",\"about\":\"Updated bio\"}"));
    }

    // Test for deleting a user
    @Test
    void testDeleteUser() throws Exception {
        // Mock the service call
        Mockito.doNothing().when(userService).deleteUser(1);

        // Expected response map
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "user deleted successfully");

        // Perform DELETE request
        mockMvc.perform(delete("/api/users/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"user deleted successfully\"}"));
    }

    // Test for getting all users
    @Test
    void testGetAllUsers() throws Exception {
        // Mock list of UserDto
        UserDto user1 = new UserDto();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("987432");
        user1.setAbout("bio1");

        UserDto user2 = new UserDto();
        user2.setId(2);
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("kiahsf");
        user2.setAbout("bio2");

        List<UserDto> users = Arrays.asList(user1, user2);

        // Mock the service call
        Mockito.when(userService.getAllUser()).thenReturn(users);

        // Perform GET request
        mockMvc.perform(get("/api/users/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"987432\",\"about\":\"bio1\"}," +
                        "{\"id\":2,\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\",\"password\":\"kiahsf\",\"about\":\"bio2\"}]"));
    }

    // Test for getting a user by ID
    @Test
    void testGetUserById() throws Exception {
        // Mock UserDto
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("klsjf");
        userDto.setAbout("lskd");

        // Mock the service call
        Mockito.when(userService.getUserById(1)).thenReturn(userDto);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/getById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"klsjf\",\"about\":\"lskd\"}"));
    }
}
