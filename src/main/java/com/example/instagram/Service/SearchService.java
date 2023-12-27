package com.example.instagram.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.Model.User;
import com.example.instagram.Repository.UserRepository;

@Service
public class SearchService {

    @Autowired
    UserRepository repo;

    public Set<User> searchUsersByName(String key) {
        return repo.searchUserByName(key);
    }

    public Set<User> searchUsersByUsername(String key)
    {
        return repo.searchUserByUsername(key);
    }
    
}
