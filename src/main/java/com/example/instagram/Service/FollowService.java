package com.example.instagram.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.DTO.Recommendation;
import com.example.instagram.DTO.SuggestedRecommendations;
import com.example.instagram.Exception.UserNotFoundException;
import com.example.instagram.Model.User;
import com.example.instagram.Repository.UserRepository;

@Service
public class FollowService {
    @Autowired
    UserRepository repo;

    @Autowired
    NotificationService notify;

    public String followUser(String requester, String receiver) throws Exception {
        Optional<User> requestorWrapper = repo.findById(requester);
        Optional<User> receiverWrapper = repo.findById(receiver);
        if (requestorWrapper.isPresent() && receiverWrapper.isPresent()) {
            User follower = requestorWrapper.get();
            User following = receiverWrapper.get();
            following.getFollowers().add(follower);
            follower.getFollowing().add(following);
            repo.saveAndFlush(following);
            repo.saveAndFlush(follower);

            notify.followNotification(follower, following);
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
            repo.saveAndFlush(following);
            repo.saveAndFlush(follower);
            notify.unfollowNotification(follower.getUsername(), following.getUsername());
            return "Unfollow request sent from " + requester + " to " + receiver + " successfully.";
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

    public Set<Recommendation> getRecommendation(String username) throws Exception {
        Optional<User> userWrapper = repo.findById(username);
        if (userWrapper.isPresent()) {
            SuggestedRecommendations result = new SuggestedRecommendations();
            for (User common : userWrapper.get().getFollowing()) {
                for (User user : common.getFollowing()) {

                    /**
                     * if the user is not current user or among the list of people user is already
                     * following
                     */
                    if (user != userWrapper.get() && !userWrapper.get().getFollowing().contains(user)) {
                        /**
                         * if user is already in list add common to the list
                         * else add common and user to list
                         */
                        if (result.getRecommendations().get(user) == null) {
                            result.getRecommendations().put(user, new Recommendation());
                            result.getRecommendations().get(user).setUser(user);
                        }
                        result.getRecommendations().get(user).getCommon().add(common);
                    }
                }
            }
            Set<Recommendation> fin = new HashSet<>();
            fin.addAll(result.getRecommendations().values());
            return fin;
        } else {
            throw new UserNotFoundException();
        }
    }

    public Collection<Recommendation> getPopularUsers(String username) {
        Set<User> result = repo.getMostPopularUsers(username);
        Set<Recommendation> recommedation = new HashSet<>();
        for (User user : result) {
            Recommendation rec = new Recommendation();
            rec.setUser(user);
            recommedation.add(rec);
        }
        return recommedation;
    }

}
