package com.example.instagram.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.instagram.Model.Post;
import com.example.instagram.Model.User;

public class PostDTO {
    String src;
    String username;
    Long id;
    Long commentCount, likeCount;
    Date creationDate;
    String description;
    String userSrc;
    List<String> likedBy = new ArrayList<>();

    public void builder(Post post) {
        src = post.getPostLink();
        commentCount = (long) post.getComments().size();
        likeCount = (long) post.getLikedBy().size();
        username = post.getKey().getUsername();
        id = post.getKey().getId();
        creationDate = post.getCreationDate();
        description = post.getDescription();
        for (User user : post.getLikedBy()) {
            likedBy.add(user.getUsername());
        }
    }

    public String getUserSrc() {
        return this.userSrc;
    }

    public void setUserSrc(String userSrc) {
        this.userSrc = userSrc;
    }

    public List<String> getLikedBy() {
        return this.likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
