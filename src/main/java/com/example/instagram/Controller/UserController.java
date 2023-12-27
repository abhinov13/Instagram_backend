package com.example.instagram.Controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.DTO.UserBuilder;
import com.example.instagram.Model.User;
import com.example.instagram.Service.FileUploadService;
import com.example.instagram.Service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    FileUploadService fileService;

    @PostMapping("/create")
    public @ResponseBody User registerUser(@RequestBody UserBuilder user) {
        return userService.createUser(user.build());
    }

    @PostMapping("/updateProfilePicture")
    public @ResponseBody User uploadProfilePicture(@RequestParam("username") String username,
            @RequestParam("file") MultipartFile file)
            throws Exception {
        System.out.println("called");
        List<String> localFileInfo = fileService.storeToLocal(file, (username));
        System.out.println("stored to loca;");
        String imageLink = fileService.storeToCloud(localFileInfo.get(0), localFileInfo.get(1));
        System.out.println("stored to cloud");
        User user = userService.updateProfileImage(username, imageLink);
        System.out.println("success");
        return user;
    }

    @PostMapping("/removeProfilePicture")
    public @ResponseBody User removeProfilePicture(@RequestBody String username) throws Exception
    {
        return userService.removeProfileImage(username);
    }

    @PatchMapping("/updateUser/{username}")
    public @ResponseBody User updateUser(@PathVariable String username, @RequestBody Map<Object, Object> updatedProperties)
            throws Exception {
        return userService.updateUser(username, updatedProperties);
    }

    @PostMapping("/validate/username")
    public @ResponseBody String validateUsername(@RequestBody String username) {
        return userService.validateUsername(username);
    }

    @PostMapping("/validate/mobileOrEmail")
    public @ResponseBody Boolean validateMobileEmail(@RequestBody String mobileOrEmail) {
        System.out.println(mobileOrEmail);
        if (mobileOrEmail.indexOf("@") == -1)
            return userService.validateMobile(mobileOrEmail);
        else
            return userService.validateEmail(mobileOrEmail);
    }

    @PostMapping("/authenticate")
    public @ResponseBody Boolean authenticate(@RequestBody Map<Object, Object> request) {
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        return userService.authenticate(username, password);
    }

    @GetMapping("/getUser/{username}")
    public @ResponseBody User getUser(@PathVariable String username) throws Exception {
        return userService.getUser(username);
    }
}
