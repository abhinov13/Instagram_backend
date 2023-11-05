package com.example.instagram.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.instagram.Service.FileUploadService;
import com.example.instagram.Service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    FileUploadService fileService;
    @Autowired
    PostService postService;

    @PostMapping("/upload")
    public String postUpload(@RequestBody Map<String, Object> map)
    {
        return null;
    }
}
