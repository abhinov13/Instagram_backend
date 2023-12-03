package com.example.instagram.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.Exception.UserNotFoundException;
import com.example.instagram.Model.User;
import com.example.instagram.Repository.UserRepository;

@Service
public class FollowService {
    @Autowired
    UserRepository repo;

    public String followUser(String requester, String receiver) throws Exception {
        Optional<User> requestorWrapper = repo.findById(requester);
        Optional<User> receiverWrapper = repo.findById(receiver);
        if (requestorWrapper.isPresent() && receiverWrapper.isPresent()) {
            User follower = requestorWrapper.get();
            User following = receiverWrapper.get();
            following.getFollowers().add(follower);
            follower.getFollowing().add(following);
            repo.save(following);
            repo.save(follower);
            return "Follow request sent from " + requester + " to " + receiver + " successfully.";
        } else {
            throw new UserNotFoundException();
        }
    }

    public String unfollowUser(String requester, String receiver) throws Exception {
        Optional<User> requestorWrapper = repo.findById(requester);
        Optional<User> receiverWrapper = repo.findById(receiver);
        if (requestorWrapper.isPresent() && receiverWrapper.isPresent()) {
            User follower = requestorWrapper.get();
            User following = receiverWrapper.get();
            following.getFollowers().remove(follower);
            follower.getFollowing().remove(following);
            repo.save(following);
            repo.save(follower);
            return "unfollow request sent from " + requester + " to " + receiver + " successfully.";
        } else {
            throw new UserNotFoundException();
        }
    }

    public Set<User> getFollowRequests(String username) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            User user = userWrapper.get();
            Set<User> result = user.getFollowers();
            result.removeAll(user.getFollowing());
            return result;
        } else {
            throw new UserNotFoundException();
        }
    }

    public Set<User> getFollowers(String username) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            return userWrapper.get().getFollowers();
        } else {
            throw new UserNotFoundException();
        }
    }

    public Set<User> getFollowing(String username) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            return userWrapper.get().getFollowing();
        } else {
            throw new UserNotFoundException();
        }
    }

}
