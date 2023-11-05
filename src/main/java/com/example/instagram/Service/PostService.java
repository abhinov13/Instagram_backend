package com.example.instagram.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.instagram.Repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository repo;
    
    public String getMaxId(String username)
    {
        return null;
    }
}
