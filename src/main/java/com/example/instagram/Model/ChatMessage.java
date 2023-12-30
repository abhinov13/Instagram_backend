package com.example.instagram.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String message;

    @ManyToOne
    @JoinColumn(referencedColumnName = "username")
    User sender;
    Date creationDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    ChatLobby lobby;

    public ChatMessage() {
        creationDate = new Date();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ChatLobby getLobby() {
        return this.lobby;
    }

    public void setLobby(ChatLobby lobby) {
        this.lobby = lobby;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", message='" + getMessage() + "'" +
            ", sender='" + getSender().getUsername() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", lobby='" + getLobby() + "'" +
            "}";
    }
}
