package com.example.instagram.Controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.Exception.InvalidFileException;
import com.example.instagram.Service.FileUploadService;
import com.example.instagram.Service.PostService;

@Controller
@RequestMapping("/posthandler")
public class PostController {
    @Autowired
    FileUploadService fileService;
    @Autowired
    PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<?> postUpload(@RequestParam("username") String username,@RequestParam("description") String description,@RequestParam("file") MultipartFile file) throws Exception
    {
        /*
         * Logic to be written here
         * Upload the file to local storage
         * Once Upload push it to s3
         * Once pushed get the link from s3 and create Post object with it
         */
        Long nextId = postService.getNextId(username);
        System.out.println("Original filename");
        System.out.println(file.getOriginalFilename());
        if(nextId == null)
        {
            nextId = (long) 1;
        }
        if(username == null || nextId == null) throw new InvalidFileException();
        System.out.println(fileService.storeToLocal(file, (username + "." + nextId)));
        // make all service and everything to upload to s3
        String postLink = "Done";
        //postService.createPost(postLink, description, username);
        return ResponseEntity.ok().body("Done");
    }
}
