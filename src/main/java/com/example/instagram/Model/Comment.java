package com.example.instagram.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.example.instagram.Model.KeyClass.CommentKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comment {
    Date creationDate;
    @EmbeddedId
    @GenericGenerator(name = "CommentKeyGenerator", strategy = "com.example.instagram.Model.IdGenerators.CommentKeyGenerator")
    @GeneratedValue(generator = "CommentKeyGenerator")
    CommentKey key;
    String comment;

    @JsonIgnore
    @ManyToOne
    @Nullable
    @JoinColumns({
        @JoinColumn(name = "postUsername", referencedColumnName = "username", nullable = true),
        @JoinColumn(name = "postId", referencedColumnName = "id", nullable = true)
    })
    Post post;

    @JsonIgnore
    @OneToMany(mappedBy = "replyTo")
    Set<Comment> replyBy = new HashSet<>();
    @ManyToOne
    // @MapsId("replyBy")
    @Nullable
    @JoinColumns({ @JoinColumn(name = "replyToUsername", referencedColumnName = "id", nullable = true),
            @JoinColumn(name = "replyToId", referencedColumnName = "username", nullable = true) })
    Comment replyTo;

    public Comment() {
        creationDate = new Date();
    }

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment [key=" + key + ", comment=" + comment + ", post=" + post + ", replyBy=" + replyBy + ", replyTo="
                + replyTo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

}
