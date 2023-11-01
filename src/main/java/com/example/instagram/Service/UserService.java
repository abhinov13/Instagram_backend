package com.example.instagram.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.Model.User;
import com.example.instagram.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repo;

    public User createUser(User user)
    {
        return repo.save(user);
    }
}
