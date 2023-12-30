package com.example.instagram.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.instagram.Model.ChatLobby;
import com.example.instagram.Model.ChatMessage;
import com.example.instagram.Model.User;

public class DetailedLobby {
    List<User> users = new ArrayList<>();
    Long id;
    ChatMessageDTO lastMessage;

    public DetailedLobby() {

    }

    public DetailedLobby(ChatLobby chatLobby, ChatMessage lastMessage) {
        id = chatLobby.getId();
        for (User user : chatLobby.getParticipants()) {
            users.add(user);
        }

        if (lastMessage != null) {
            this.lastMessage = new ChatMessageDTO(lastMessage);
        }

    }

    public ChatMessageDTO getLastMessage() {
        return this.lastMessage;
    }

    public void setLastMessage(ChatMessageDTO lastMessage) {
        this.lastMessage = lastMessage;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
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
                ", lastMessage='" + getLastMessage() + "'" +
                "}";
    }

}
