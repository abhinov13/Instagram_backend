package com.example.instagram.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.instagram.Model.User;
import com.example.instagram.Service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService service;

    @PostMapping("/user")
    public @ResponseBody Set<User> searchUser(@RequestBody String key)
    {
        Set<User> users = new HashSet<>();
        if(key.indexOf(" ") == -1)
        {
            users.addAll(service.searchUsersByUsername(key));
        }
        
        users.addAll(service.searchUsersByName(key));
        return users;
    }
}

