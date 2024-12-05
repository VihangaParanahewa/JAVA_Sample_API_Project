package com.example.JAVA_Sample_API_Project.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.JAVA_Sample_API_Project.dto.UserCreateDTO;
import com.example.JAVA_Sample_API_Project.dto.UserDTO;
import com.example.JAVA_Sample_API_Project.entity.User;
import com.example.JAVA_Sample_API_Project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User mockUser;
    private UserDTO mockUserDTO;
    private UserCreateDTO mockUserCreateDTO;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1);
        mockUser.setName("John Doe");
        mockUser.setAddress("123 Main St");

        mockUserDTO = new UserDTO();
        mockUserDTO.setId(1);
        mockUserDTO.setName("John Doe");
        mockUserDTO.setAddress("123 Main St");

        mockUserCreateDTO = new UserCreateDTO();
        mockUserCreateDTO.setName("John Doe");
        mockUserCreateDTO.setAddress("123 Main St");
    }

    @Test
    void testGetAllUsers_ReturnsListOfUsers() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Collections.singletonList(mockUser));
        when(modelMapper.map(mockUser, UserDTO.class)).thenReturn(mockUserDTO);

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_ReturnsEmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(List.of());

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveUser_ReturnsSavedUser() {
        // Arrange
        when(modelMapper.map(mockUserCreateDTO, User.class)).thenReturn(mockUser);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(modelMapper.map(mockUser, UserDTO.class)).thenReturn(mockUserDTO);

        // Act
        UserDTO result = userService.saveUser(mockUserCreateDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testUpdateUser_UpdatesExistingUser() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(modelMapper.map(mockUser, UserDTO.class)).thenReturn(mockUserDTO);

        mockUserDTO.setName("Jane Doe");
        mockUser.setName("Jane Doe");

        // Act
        UserDTO result = userService.updateUser(mockUserDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testUpdateUser_ThrowsExceptionWhenUserNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.updateUser(mockUserDTO));
        assertEquals("User not found with ID: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteUser_SuccessfulDeletion() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        // Act
        String result = userService.deleteUser(1);

        // Assert
        assertEquals("User deleted successfully with ID: 1", result);
        verify(userRepository, times(1)).delete(mockUser);
    }

    @Test
    void testDeleteUser_ThrowsExceptionWhenUserNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.deleteUser(1));
        assertEquals("User not found with ID: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }
}

