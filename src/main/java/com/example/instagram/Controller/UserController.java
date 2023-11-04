package com.example.instagram.Controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.instagram.Model.User;
import com.example.instagram.Service.UserService;

@CrossOrigin
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

    @PatchMapping("/updateUser/{username}")
    public User updateUser(@PathVariable String username, @RequestBody Map<Object,Object> updatedProperties) throws Exception
    {
        return userService.updateUser(username, updatedProperties);
    }

    @PostMapping("/validate/username")
    public String validateUsername(@RequestBody String username)
    {
        return userService.validateUsername(username);
    }

    @PostMapping("/validate/mobileOrEmail")
    public Boolean validateMobileEmail(@RequestBody String mobileOrEmail)
    {
        if(mobileOrEmail.matches("([0-9]+)"))
        return userService.validateMobile(mobileOrEmail);
        else
        return userService.validateEmail(mobileOrEmail);
    }

    @PostMapping("/authenticate")
    public Boolean authenticate(@RequestBody Map<Object,Object> request)
    {
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        return userService.authenticate(username, password);
    }
}
