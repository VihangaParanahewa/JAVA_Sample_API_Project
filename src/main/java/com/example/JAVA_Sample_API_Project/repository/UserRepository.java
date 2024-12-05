package com.example.JAVA_Sample_API_Project.repository;

import com.example.JAVA_Sample_API_Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
