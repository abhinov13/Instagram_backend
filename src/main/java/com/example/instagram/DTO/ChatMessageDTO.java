package com.example.instagram.DTO;

import com.example.instagram.Model.ChatMessage;

public class ChatMessageDTO {
    String message;
    String sender;
    Long lobbyId;
    String senderProfileUrl;

    public ChatMessageDTO() {

    }

    public ChatMessageDTO(ChatMessage chatMessage) {
        message = chatMessage.getMessage();
        sender = chatMessage.getSender().getUsername();
        lobbyId = chatMessage.getLobby().getId();
        senderProfileUrl = chatMessage.getSender().getProfilePictureUrl();
    }

    public String getSenderProfileUrl() {
        return this.senderProfileUrl;
    }

    public void setSenderProfileUrl(String senderProfileUrl) {
        this.senderProfileUrl = senderProfileUrl;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getLobbyId() {
        return this.lobbyId;
    }

    public void setLobbyId(Long lobbyId) {
        this.lobbyId = lobbyId;
    }

}
