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

    String profilePictureUrl;

    @JsonIgnore
    @Column(unique = true)
    String mobile;
    @JsonIgnore
    @Column(unique = true)
    String email;
    @JsonIgnore
    String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "post_likes", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = {
            @JoinColumn(name = "postUsername", referencedColumnName = "username"),
            @JoinColumn(name = "postId", referencedColumnName = "id")
    })
    Set<Post> likedPost = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    Set<Comment> likedComments = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "followers", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"), inverseJoinColumns = @JoinColumn(name = "followerUsername", referencedColumnName = "username"))
    Set<User> followers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    Set<User> following = new HashSet<>();

    public void setAllPropertiesNull() {
        followers = null;
        following = null;
        likedPost = null;
        name = null;
        username = null;
        mobile = null;
        password = null;
        email = null;
    }

    public Set<Comment> getLikedComments() {
        return this.likedComments;
    }

    public void setLikedComments(Set<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

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

    public User() {
    }

    public User(String name, String username, String profilePictureUrl, String mobile, String email, String password,
            Set<Post> likedPost, Set<Comment> likedComments, Set<User> followers, Set<User> following) {
        this.name = name;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.likedPost = likedPost;
        this.likedComments = likedComments;
        this.followers = followers;
        this.following = following;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User profilePictureUrl(String profilePictureUrl) {
        setProfilePictureUrl(profilePictureUrl);
        return this;
    }

    public User mobile(String mobile) {
        setMobile(mobile);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User likedPost(Set<Post> likedPost) {
        setLikedPost(likedPost);
        return this;
    }

    public User likedComments(Set<Comment> likedComments) {
        setLikedComments(likedComments);
        return this;
    }

    public User followers(Set<User> followers) {
        setFollowers(followers);
        return this;
    }

    public User following(Set<User> following) {
        setFollowing(following);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return user.getUsername().equals(username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

}