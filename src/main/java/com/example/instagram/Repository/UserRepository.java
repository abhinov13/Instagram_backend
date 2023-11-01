package com.example.instagram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.instagram.Model.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
