package com.example.instagram.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class ChatLobby {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToMany
    @JoinTable(name = "lobbyParticipants", joinColumns = @JoinColumn(referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "username"))
    Set<User> participants;
    Boolean isPersonalChat;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public Boolean isIsPersonalChat() {
        return this.isPersonalChat;
    }

    public Boolean getIsPersonalChat() {
        return this.isPersonalChat;
    }

    public void setIsPersonalChat(Boolean isPersonalChat) {
        this.isPersonalChat = isPersonalChat;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", participants='" + getParticipants() + "'" +
                ", isPersonalChat='" + isIsPersonalChat() + "'" +
                "}";
    }

}

/**
 * 1 user can take part in manny lobbies
 * 1 lobby can have many users
 */