package com.example.instagram.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.Model.User;

@Service
public class FileUploadService {
    String path = System.getProperty("user.dir") + "\\uploads";
    public String uploadPostImage(User user,MultipartFile file)
    {
        return path;
    }
}
