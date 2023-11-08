package com.example.instagram.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.Model.Post;
import com.example.instagram.Repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository repo;

    public void createPost(String postLink, String description, String username) {
        Post post = new Post();
        post.setPostLink(postLink);
        post.setDescription(description);
        post.setUsername(username);
        repo.save(post);
    }

    public Long getNextId(String username) {
        return repo.getMaxPostId(username);
    }
    

}
