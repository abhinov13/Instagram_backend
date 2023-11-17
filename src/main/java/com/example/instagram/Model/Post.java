package com.example.instagram.Model;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import com.example.instagram.Model.KeyClass.PostKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
}
