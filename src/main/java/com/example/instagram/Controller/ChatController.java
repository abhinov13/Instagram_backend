package com.example.instagram.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.instagram.DTO.ChatMessageDTO;
import com.example.instagram.DTO.DetailedLobby;
import com.example.instagram.DTO.Lobby;
import com.example.instagram.Model.ChatLobby;
import com.example.instagram.Model.ChatMessage;
import com.example.instagram.Service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService service;

    @PostMapping("/createPrivateLobby") // add logic to get existing lobby if it exists
    public @ResponseBody Lobby generatePrivateLobby(@RequestBody Lobby lobby) throws Exception {
        return new Lobby(service.createPrivateLobby(lobby.getUsers().get(0), lobby.getUsers().get(1)));
    }

    @PostMapping("/message")
    public @ResponseBody ChatMessageDTO sendMessage(@RequestBody ChatMessageDTO messageDTO) throws Exception {
        return new ChatMessageDTO(service.createMessage(messageDTO));
    }

    @GetMapping("/getLobbies/{username}")
    public @ResponseBody List<DetailedLobby> getLobbies(@PathVariable String username) throws Exception {
        List<ChatLobby> lobbyModels = service.getLobby(username);
        List<DetailedLobby> dtoSet = new ArrayList<>();
        for (ChatLobby lobby : lobbyModels) {
            ChatMessage message = service.getLastMessage(lobby.getId());
            dtoSet.add(new DetailedLobby(lobby, message));
        }
        return dtoSet;
    }

    @GetMapping("/getMessages/{id}")
    public @ResponseBody List<ChatMessageDTO> getMessages(@PathVariable Long id) {
        List<ChatMessage> messages = service.getMessages(id);
        List<ChatMessageDTO> dtoList = new ArrayList<>();
        for (ChatMessage message : messages) {
            dtoList.add(new ChatMessageDTO(message));
        }
        return dtoList;
    }
}
