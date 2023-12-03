package com.example.instagram.Controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.instagram.Model.User;
import com.example.instagram.Service.FollowService;

@Controller
public class FollowersController {
    @Autowired
    FollowService service;

    @PostMapping("/follow")
    public @ResponseBody String followUser(@RequestBody Map<String, Object> map) throws Exception {
        String requester = (String) map.get("sender");
        String receiver = (String) map.get("receiver");

        return service.followUser(requester, receiver);
    }

    @PostMapping("/unfollow")
    public @ResponseBody String unfollowUser(@RequestBody Map<String, Object> map) throws Exception {
        String requester = (String) map.get("sender");
        String receiver = (String) map.get("receiver");

        return service.unfollowUser(requester, receiver);
    }

    @GetMapping("getFollowerRequests/{username}")
    public @ResponseBody Set<User> getFollowRequests(@PathVariable String username) throws Exception {
        return service.getFollowRequests(username);
    }

    @GetMapping("/getFollowers/{username}")
    public @ResponseBody Set<User> getFollowers(@PathVariable String username) throws Exception {
        return service.getFollowers(username);
    }

    @GetMapping("/getFollowing/{username}")
    public @ResponseBody Set<User> getFollowing(@PathVariable String username) throws Exception {
        return service.getFollowing(username);
    }

    @GetMapping("getFollowerCount/{username}")
    public @ResponseBody Integer getFollowerCount(@PathVariable String username) throws Exception {
        return service.getFollowers(username).size();
    }

    @GetMapping("getFollowingCount/{username}")
    public @ResponseBody Integer getFollowingCount(@PathVariable String username) throws Exception {
        return service.getFollowing(username).size();
    }

}