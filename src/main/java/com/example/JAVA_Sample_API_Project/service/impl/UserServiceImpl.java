package com.example.JAVA_Sample_API_Project.service.impl;

import com.example.JAVA_Sample_API_Project.dto.UserCreateDTO;
import com.example.JAVA_Sample_API_Project.dto.UserDTO;
import com.example.JAVA_Sample_API_Project.entity.User;
import com.example.JAVA_Sample_API_Project.repository.UserRepository;
import com.example.JAVA_Sample_API_Project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO saveUser(UserCreateDTO userCreateDTO) {
        User user = modelMapper.map(userCreateDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userDTO.getId()));
        existingUser.setName(userDTO.getName());
        existingUser.setAddress(userDTO.getAddress());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteUser(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        userRepository.delete(existingUser);
        return "User deleted successfully with ID: " + id;
    }
}
