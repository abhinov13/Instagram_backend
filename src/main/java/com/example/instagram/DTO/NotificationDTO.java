package com.example.instagram.DTO;

import java.util.Date;

import com.example.instagram.Model.Notification;

public class NotificationDTO {
    String from;
    String to;
    Long id;
    String type;
    Long postId;
    String postUsername;
    Date date;
    String seen;
    String fromUrl;

    public void build(Notification notification) {
        from = notification.getFrom().getUsername();
        to = notification.getTo().getUsername();
        id = notification.getId();
        type = notification.getType();
        date = notification.getDate();
        seen = notification.getSeen();
        fromUrl = notification.getFrom().getProfilePictureUrl();
        if (notification.getPost() != null) {
            postId = notification.getPost().getKey().getId();
            postUsername = from;
        }

    }

    public String getFromUrl() {
        return this.fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostUsername() {
        return this.postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSeen() {
        return this.seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

}
