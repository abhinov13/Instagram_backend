package com.example.instagram.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.instagram.Model.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(nativeQuery = true, value = """
            SELECT   *
            FROM     chat_message
            WHERE    lobby_id = ?1
            ORDER BY creation_date
            """)
    public List<ChatMessage> getMessages(Long id);

    @Query(nativeQuery = true, value = """
            SELECT   message.*
            FROM     chat_message message
            JOIN     chat_lobby lobby
            ON       lobby.id = message.lobby_id
            WHERE    lobby.id = ?1
            ORDER BY message.creation_date DESC
            LIMIT 1
                """)
    public ChatMessage getLastMessage(Long id);
}
