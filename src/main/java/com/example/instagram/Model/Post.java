package com.example.instagram.Model;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.example.instagram.Model.KeyClass.PostKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(PostKey.class)
public class Post {
    @Column(name = "link", unique = true)
    String postLink;
    String description;
    Date creationDate;
    @Id
    String username;
    @Id
    @GenericGenerator(name = "postIdGenerator", strategy = "com.example.instagram.Model.IdGenerators.PostKeyGenerator")
    @GeneratedValue(generator = "postIdGenerator")
    String id;

    public Post() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
