package com.example.JAVA_Sample_API_Project.service;

import com.example.JAVA_Sample_API_Project.dto.UserCreateDTO;
import com.example.JAVA_Sample_API_Project.dto.UserDTO;

import java.util.List;

/**
 * Service interface for user operations.
 */
public interface UserService {

    /**
     * Retrieve all users.
     *
     * @return a list of all users as UserDTO objects.
     */
    List<UserDTO> getAllUsers();

    /**
     * Create a new user.
     *
     * @param userCreateDTO the user creation data.
     * @return the created user as a UserDTO object.
     */
    UserDTO saveUser(UserCreateDTO userCreateDTO);

    /**
     * Update an existing user.
     *
     * @param userDTO the user data to update.
     * @return the updated user as a UserDTO object.
     */
    UserDTO updateUser(UserDTO userDTO);

    /**
     * Delete a user by ID.
     *
     * @param id the ID of the user to delete.
     * @return a success message.
     */
    String deleteUser(Integer id);
}
