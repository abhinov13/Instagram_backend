package com.example.instagram.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Date date;
    String seen;

    String type;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username")
    User to;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username")
    User from;

    @ManyToOne
    @JoinColumns({ @JoinColumn(referencedColumnName = "username"),
            @JoinColumn(referencedColumnName = "id") })
    Post post;

    public Notification()
    {
        date = new Date();
        seen = "unseen";
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

    public User getTo() {
        return this.to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getFrom() {
        return this.from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
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
