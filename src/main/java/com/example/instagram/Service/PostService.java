package com.example.instagram.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.instagram.Exception.PostNotFoundException;
import com.example.instagram.Exception.UserNotFoundException;
import com.example.instagram.Model.Post;
import com.example.instagram.Model.User;
import com.example.instagram.Model.KeyClass.PostKey;
import com.example.instagram.Repository.PostRepository;
import com.example.instagram.Repository.UserRepository;

@Service
public class PostService {
    @Autowired
    PostRepository repo;

    @Autowired
    UserRepository userRepo;

    public Post createPost(String postLink, String description, String username) {
        try {
            Post post = new Post();
            post.setPostLink(postLink);
            post.setDescription(description);
            post.setKey(new PostKey());
            post.getKey().setUsername(username);
            return repo.save(post);
        } catch (Exception e) {
            return null;
        }
    }

    public Long getNextId(String username) {
        return repo.getMaxPostId(username);
    }

    public List<Post> getPosts() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));
    }

    public Post likePost(String username, String postUsername, Long postId) throws Exception
    {
        PostKey key = new PostKey(postUsername, postId);
        Optional<Post> postWrapper = repo.findById(key);
        if(postWrapper.isPresent())
        {
            Optional<User> userWrapper = userRepo.findById(username);
            if(userWrapper.isPresent())
            {
                Post post = postWrapper.get();
                User user = userWrapper.get();

                user.getLikedPost().add(post);
                post.getLikedBy().add(user);

                userRepo.saveAndFlush(user);
                return repo.saveAndFlush(post);
            }
            else{
                throw new UserNotFoundException();
            }
        }
        else{
            throw new PostNotFoundException();
        }
    }

    public Post unlikePost(String username, String postUsername, Long postId) throws Exception {
        PostKey key = new PostKey(postUsername, postId);
        Optional<Post> postWrapper = repo.findById(key);
        if(postWrapper.isPresent())
        {
            Optional<User> userWrapper = userRepo.findById(username);
            if(userWrapper.isPresent())
            {
                Post post = postWrapper.get();
                User user = userWrapper.get();

                user.getLikedPost().remove(post);
                post.getLikedBy().remove(user);

                userRepo.saveAndFlush(user);
                return repo.saveAndFlush(post);
            }
            else{
                throw new UserNotFoundException();
            }
        }
        else{
            throw new PostNotFoundException();
        }
    }

}
