package com.example.instagram.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.Model.Post;
import com.example.instagram.Model.KeyClass.PostKey;
import com.example.instagram.Repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository repo;

    public Post createPost(String postLink, String description, String username) {
        try {
            Post post = new Post();
            post.setPostLink(postLink);
            post.setDescription(description);
            post.setKey(new PostKey());
            post.getKey().setUsername(username);
            return repo.save(post);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println(e.getSuppressed());
            System.out.println(e.getSuppressed().toString());
            return null;
        }
    }

    public Long getNextId(String username) {
        return repo.getMaxPostId(username);
    }

    public List<Post> getPosts() {
        return repo.findAll();
    }

}
