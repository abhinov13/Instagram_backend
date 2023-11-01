package com.example.instagram.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.Exception.UsernameTaken;
import com.example.instagram.Model.User;
import com.example.instagram.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public User registerUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/")
    public Object testGet() throws Exception
    {
        throw new Exception();
    }

}
