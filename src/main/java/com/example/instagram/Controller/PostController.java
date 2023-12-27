package com.example.instagram.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.DTO.PostDTO;
import com.example.instagram.Exception.InvalidFileException;
import com.example.instagram.Model.Post;
import com.example.instagram.Service.FileUploadService;
import com.example.instagram.Service.PostService;

@Controller
@RequestMapping("/posthandler")
@CrossOrigin
public class PostController {
    @Autowired
    FileUploadService fileService;
    @Autowired
    PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<Post> postUpload(@RequestParam("username") String username,
            @RequestParam("description") String description, @RequestParam("file") MultipartFile file)
            throws Exception {
        Long nextId = postService.getNextId(username);
        if (nextId == null) {
            nextId = (long) 1;
        }
        if (username == null || nextId == null)
            throw new InvalidFileException();
        List<String> localFileInfo = fileService.storeToLocal(file, (username + "." + nextId));
        String postLink = fileService.storeToCloud(localFileInfo.get(0), localFileInfo.get(1));
        Post post = postService.createPost(postLink, description, username);
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @GetMapping("/getPosts")
    public @ResponseBody List<PostDTO> getPosts() {
        List<Post> ret = postService.getPosts();
        Collections.reverse(ret);
        return postService.convertToDTO(ret);
    }

    @GetMapping("/getPosts/{username}")
    public @ResponseBody Set<PostDTO> getPostsForUser(@PathVariable String username)
    {
        return postService.getPostsForUsername(username);
    }

    @PostMapping("/like")
    public @ResponseBody Post likePost(@RequestBody Map<String, Object> params) throws Exception {
        String username = (String) params.get("username");
        String postUsername = (String) params.get("postUsername");
        Long postId = ((Number) params.get("postId")).longValue();
        return postService.likePost(username, postUsername, postId);
    }

    @PostMapping("/unlike")
    public @ResponseBody Post unlikePost(@RequestBody Map<String, Object> params) throws Exception {
        String username = (String) params.get("username");
        String postUsername = (String) params.get("postUsername");
        Long postId = ((Number) params.get("postId")).longValue();
        return postService.unlikePost(username, postUsername, postId);
    }

}
