package com.example.instagram.Model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class User {
    @NonNull
    String name;
    @Id
    String username;
    @Column(unique = true)
    String mobile;
    @Column(unique = true)
    String email;
    String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "post_likes", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = {
            @JoinColumn(name = "postUsername", referencedColumnName = "username"),
            @JoinColumn(name = "postId", referencedColumnName = "id")
    })
    Set<Post> likedPost = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "followers", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"), inverseJoinColumns = @JoinColumn(name = "followerUsername", referencedColumnName = "username"))
    Set<User> followers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    Set<User> following = new HashSet<>();

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<Post> getLikedPost() {
        return likedPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLikedPost(Set<Post> likedPost) {
        this.likedPost = likedPost;
    }

}