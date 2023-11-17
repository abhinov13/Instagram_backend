package com.example.instagram.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.example.instagram.Model.KeyClass.CommentKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;

@Entity
public class Comment {
    Date creationDate;
    @EmbeddedId
    CommentKey key;
    String comment;

    @OneToMany(mappedBy = "replyTo")
    Set<Comment> replyBy = new HashSet<>();
    @ManyToOne
    @MapsId("replyBy")
    Comment replyTo;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CommentKey getKey() {
        return key;
    }

    public void setKey(CommentKey key) {
        this.key = key;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Comment> getReplyBy() {
        return replyBy;
    }

    public void setReplyBy(Set<Comment> replyBy) {
        this.replyBy = replyBy;
    }

    public Comment getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }

}
