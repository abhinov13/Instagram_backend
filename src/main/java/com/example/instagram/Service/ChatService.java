package com.example.instagram.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.DTO.ChatMessageDTO;
import com.example.instagram.Exception.UserNotFoundException;
import com.example.instagram.Model.ChatLobby;
import com.example.instagram.Model.ChatMessage;
import com.example.instagram.Model.User;
import com.example.instagram.Repository.ChatLobbyRepository;
import com.example.instagram.Repository.ChatMessageRepository;
import com.example.instagram.Repository.UserRepository;

@Service
public class ChatService {
    @Autowired
    ChatMessageRepository messageRepo;
    @Autowired
    ChatLobbyRepository lobbyRepo;
    @Autowired
    UserRepository userRepo;

    @Autowired
    NotificationService notify;

    public ChatLobby createPrivateLobby(String username1, String username2) throws Exception {
        ChatLobby lobby = lobbyRepo.findPrivateChatLobby(username1, username2);
        if (lobby != null)
            return lobby;
        User user1 = userRepo.findById(username1).orElseThrow(() -> new UserNotFoundException());
        User user2 = userRepo.findById(username2).orElseThrow(() -> new UserNotFoundException());
        lobby = new ChatLobby();
        lobby.setIsPersonalChat(true);
        Set<User> participants = new HashSet<>();
        participants.add(user1);
        participants.add(user2);
        lobby.setParticipants(participants);
        return lobbyRepo.save(lobby);
    }

    public ChatMessage createMessage(ChatMessageDTO message) throws Exception {
        User user = userRepo.findById(message.getSender()).orElseThrow(() -> new UserNotFoundException());
        ChatLobby lobby = lobbyRepo.findById(message.getLobbyId()).orElseThrow();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setLobby(lobby);
        chatMessage.setSender(user);
        chatMessage.setMessage(message.getMessage());

        notify.sendMessageNotification(chatMessage);

        return messageRepo.save(chatMessage);
    }

    public List<ChatMessage> getMessages(Long id) {
        return messageRepo.getMessages(id);
    }

    public List<ChatLobby> getLobby(String username) {
        return lobbyRepo.getLobbyForUser(username);
    }

    public ChatMessage getLastMessage(Long id) {
        try {
            return messageRepo.getLastMessage(id);
        } catch (Exception e) {
            return null;
        }
    }

}

/**
 * methods:
 * create lobby
 * add users to lobby
 * remove users from lobby
 * send message
 * get messages
 */