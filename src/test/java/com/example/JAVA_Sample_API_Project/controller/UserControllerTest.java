package com.example.JAVA_Sample_API_Project.controller;

import com.example.JAVA_Sample_API_Project.dto.UserCreateDTO;
import com.example.JAVA_Sample_API_Project.dto.UserDTO;
import com.example.JAVA_Sample_API_Project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    private UserDTO mockUserDTO;
    private UserCreateDTO mockUserCreateDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        mockUserDTO = new UserDTO();
        mockUserDTO.setId(1);
        mockUserDTO.setName("John Doe");
        mockUserDTO.setAddress("123 Main St");

        mockUserCreateDTO = new UserCreateDTO();
        mockUserCreateDTO.setName("John Doe");
        mockUserCreateDTO.setAddress("123 Main St");

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers_ReturnsListOfUsers() throws Exception {
        List<UserDTO> userList = Collections.singletonList(mockUserDTO);
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_ReturnsEmptyList() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testSaveUser_ReturnsCreatedUser() throws Exception {
        when(userService.saveUser(any(UserCreateDTO.class))).thenReturn(mockUserDTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUserCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.address").value("123 Main St"));

        verify(userService, times(1)).saveUser(any(UserCreateDTO.class));
    }

    @Test
    void testSaveUser_InvalidInput_ReturnsBadRequest() throws Exception {
        // Creating an invalid DTO with missing fields
        UserCreateDTO invalidCreateDTO = new UserCreateDTO();

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCreateDTO)))
                .andExpect(status().isBadRequest());

        // Verify that the service method is never called
        verify(userService, never()).saveUser(any(UserCreateDTO.class));
    }


    @Test
    void testUpdateUser_ReturnsUpdatedUser() throws Exception {
        when(userService.updateUser(any(UserDTO.class))).thenReturn(mockUserDTO);

        mockMvc.perform(put("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(userService, times(1)).updateUser(any(UserDTO.class));
    }

    @Test
    void testDeleteUser_ReturnsOk() throws Exception {
        // Mock the return value of deleteUser to return a success message
        when(userService.deleteUser(1)).thenReturn("User deleted successfully");

        // Perform the delete request
        mockMvc.perform(delete("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(content().string("User deleted successfully"));  // Check the response content

        // Verify that the service method was called
        verify(userService, times(1)).deleteUser(1);
    }
}
