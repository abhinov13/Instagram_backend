package com.example.instagram.Controller;

import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.instagram.DTO.CommentBody;
import com.example.instagram.Exception.CommentNotFoundException;
import com.example.instagram.Exception.PostNotFoundException;
import com.example.instagram.Model.Comment;
import com.example.instagram.Model.User;
import com.example.instagram.Model.KeyClass.CommentKey;
import com.example.instagram.Service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService service;

    @PostMapping("/add")
    public @ResponseBody Comment addComment(@RequestBody CommentBody body) throws Exception {
        if (body.getPostUserId() != null) {
            String postUsername = body.getPostUsername();
            Long postId = body.getPostUserId();
            String username = body.getUsername();
            String commentBody = body.getComment();
            return service.createPostReply(postUsername, postId, username, commentBody);
        } else {
            String parentName = body.getReplyToUsername();
            Long parentId = body.getReplyToId();
            String username = body.getUsername();
            String commentBody = body.getComment();
            return service.createCommentReply(parentName, parentId, username, commentBody);
        }
    }

    @GetMapping("/post/{postUsername}/{postId}")
    public @ResponseBody Set<Comment> getPostComment(@PathVariable String postUsername, @PathVariable Long postId)
            throws PostNotFoundException {
        return service.getPostComment(postUsername, postId);
    }

    @GetMapping("/reply/{commentUsername}/{commentId}")
    public @ResponseBody Set<Comment> getReplyComment(@PathVariable String commentUsername,
            @PathVariable Long commentId) throws CommentNotFoundException {
        return service.getReplyComment(commentUsername, commentId);
    }

    @PostMapping("/like")
    public @ResponseBody Comment likeComment(@RequestBody Map<String, Object> map) throws Exception {
        Long likedId = ((Number) map.get("likedId")).longValue();
        String likedUsername = (String) map.get("likedUsername");
        String username = (String) map.get("username");
        CommentKey key = new CommentKey();
        key.setUsername(likedUsername);
        key.setId(likedId);
        return service.likeComment(key, username);
    }

    @PostMapping("/unlike")
    public @ResponseBody Comment unlikeComment(@RequestBody Map<String, Object> map) throws Exception {
        Long likedId = ((Number) map.get("likedId")).longValue();
        String likedUsername = (String) map.get("likedUsername");
        String username = (String) map.get("username");
        CommentKey key = new CommentKey();
        key.setUsername(likedUsername);
        key.setId(likedId);
        return service.unlikeComment(key, username);
    }

    @GetMapping("/getLikes/{username}/{id}")
    public @ResponseBody Set<User> getLikes(@PathVariable String username, @PathVariable Long id) throws Exception {
        CommentKey key = new CommentKey();
        key.setId(id);
        key.setUsername(username);
        return service.getUsersWhoLiked(key);
    }
}
