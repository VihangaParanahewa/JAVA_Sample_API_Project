package com.example.JAVA_Sample_API_Project.controller;

import com.example.JAVA_Sample_API_Project.dto.UserCreateDTO;
import com.example.JAVA_Sample_API_Project.dto.UserDTO;
import com.example.JAVA_Sample_API_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller for managing user operations.
 * Handles CRUD operations for users via RESTful endpoints.
 */
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieve all users.
     *
     * @return a list of all users as UserDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Save a new user.
     *
     * @param userCreateDTO the user data for creation.
     * @return the created user as a UserDTO object.
     */
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserDTO createdUser = userService.saveUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Update an existing user.
     *
     * @param userDTO the user data to update.
     * @return the updated user as a UserDTO object.
     */
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id the ID of the user to delete.
     * @return a success message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }
}
