package com.example.instagram.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.instagram.Exception.CommentNotFoundException;
import com.example.instagram.Exception.PostNotFoundException;
import com.example.instagram.Model.Comment;
import com.example.instagram.Model.Post;
import com.example.instagram.Model.KeyClass.CommentKey;
import com.example.instagram.Model.KeyClass.PostKey;
import com.example.instagram.Repository.CommentRepository;
import com.example.instagram.Repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository repo;

    @Autowired
    PostRepository postRepo;

    public Comment createCommentReply(String parentUsername, Long parentId, String commentUsername,
            String commentBody) throws Exception {
        
        Optional<Comment> commentWrapper = repo.findById(new CommentKey(parentUsername, parentId));
        if (commentWrapper.isPresent()) {
            Comment comment = new Comment();
            Comment parentComment = commentWrapper.get();
            comment.setComment(commentBody);
            comment.setKey(new CommentKey());
            comment.getKey().setUsername(commentUsername);
            comment.setReplyTo(parentComment);
            parentComment.getReplyBy().add(comment);
            comment.setReplyTo(parentComment);
            repo.saveAndFlush(comment);
            repo.saveAndFlush(parentComment);
            return comment;
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment createPostReply(String postUsername, Long postId, String commentUsername, String commentBody)
            throws PostNotFoundException {
        System.out.println(postUsername + " " + postId + " " + commentUsername + " " + commentBody);
        Optional<Post> postWrapper = postRepo.findById(new PostKey(postUsername, postId));
        if (postWrapper.isPresent()) {
            Post post = postWrapper.get();
            Comment comment = new Comment();
            comment.setComment(commentBody);
            comment.setKey(new CommentKey());
            comment.getKey().setUsername(commentUsername);
            comment.setPost(post);
            post.getComments().add(comment);
            Comment ret = repo.saveAndFlush(comment);
            postRepo.saveAndFlush(post);
            return ret;
        } else {
            throw new PostNotFoundException();
        }
    }

    public Set<Comment> getPostComment(String username, Long id) throws PostNotFoundException {
        Optional<Post> postwrapper = postRepo.findById(new PostKey(username, id));
        if(postwrapper.isPresent())
        {
            Post post = postwrapper.get();
            return post.getComments();
        }
        else
        {
            throw new PostNotFoundException();
        }
    }

    public Set<Comment> getReplyComment(String username, Long id) throws CommentNotFoundException
    {
        Optional<Comment> commentWrapper = repo.findById(new CommentKey(username, id));
        if(commentWrapper.isPresent())
        {
            Comment comment = commentWrapper.get();
            return comment.getReplyBy();
        }
        else
        {
            throw new CommentNotFoundException();
        }
    }

}
