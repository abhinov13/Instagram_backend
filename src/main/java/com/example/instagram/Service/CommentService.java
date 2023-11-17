package com.example.instagram.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.Repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository repo;

    
}
