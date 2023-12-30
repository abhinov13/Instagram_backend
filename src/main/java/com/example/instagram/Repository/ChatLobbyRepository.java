package com.example.instagram.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.instagram.Model.ChatLobby;

@Repository
public interface ChatLobbyRepository extends JpaRepository<ChatLobby, Long> {
    @Query(nativeQuery = true, value = """
            SELECT lobby.*
            FROM   chat_lobby lobby
            JOIN   lobby_participants participants
            ON     lobby.id = chat_lobby_id
            JOIN   chat_message message
            ON     message.lobby_id = lobby.id
            WHERE  participants_username = ?1
            GROUP BY lobby.id
            ORDER BY MAX(message.creation_date) DESC
                """)
    public List<ChatLobby> getLobbyForUser(String username);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM   chat_lobby
            WHERE  is_personal_chat = 1
            AND    id IN (SELECT chat_lobby_id
                          FROM   lobby_participants
                          WHERE  participants_username = ?1)
            AND    id IN (SELECT chat_lobby_id
                          FROM   lobby_participants
                          WHERE  participants_username = ?2)
            """)
    public ChatLobby findPrivateChatLobby(String username1, String username2);

}
