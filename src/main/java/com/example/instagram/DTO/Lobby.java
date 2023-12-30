package com.example.instagram.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.instagram.Model.ChatLobby;
import com.example.instagram.Model.User;

public class Lobby {
    List<String> users = new ArrayList<>();
    Long id;

    public Lobby() {

    }

    public Lobby(ChatLobby chatLobby) {
        id = chatLobby.getId();
        for (User user : chatLobby.getParticipants()) {
            users.add(user.getUsername());
        }
    }

    public List<String> getUsers() {
        return this.users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                " users='" + getUsers() + "'" +
                ", id='" + getId() + "'" +
                "}";
    }

}
