package com.example.instagram.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import com.example.instagram.Model.KeyClass.PostKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
    @Column(name = "link", unique = true)
    String postLink;
    String description;
    Date creationDate;
    @EmbeddedId
    @GenericGenerator(name = "postIdGenerator", strategy = "com.example.instagram.Model.IdGenerators.PostKeyGenerator")
    @GeneratedValue(generator = "postIdGenerator")
    PostKey key;

    @OneToMany(mappedBy = "post")
    Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "likedPost")
    Set<User> likedBy = new HashSet<>();

    public Set<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Post() {
        creationDate = new Date();
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public PostKey getKey() {
        return key;
    }

    public void setKey(PostKey key) {
        this.key = key;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post [postLink=" + postLink + ", description=" + description + ", key=" + key + ", comments=" + comments
                + "]";
    }

}
