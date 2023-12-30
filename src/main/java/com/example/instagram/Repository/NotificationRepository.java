package com.example.instagram.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.instagram.Model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
        @Query(nativeQuery = true, value = """
                        SELECT *
                        FROM   notification
                        WHERE  to_username = ?1
                        """)
        public Set<Notification> findNotificationsByUsername(String username);

        @Query(nativeQuery = true, value = """
                        SELECT *
                        FROM   notification
                        WHERE  to_username   = ?2
                        AND    from_username = ?1
                        AND    type          = 'Friend Request'
                        """)
        public Notification findFollowNotification(String from_username, String to_username);

        @Query(nativeQuery = true, value = """
                        DELETE FROM notification
                        WHERE  to_username = ?1
                        AND    type       != 'Friend Request'
                        """)
        public void deleteForUser(String username);

        @Transactional
        @Modifying
        @Query(nativeQuery = true, value = """
                        UPDATE notification
                        SET    seen = 'seen'
                        WHERE  to_username = ?1
                        AND    seen != 'seen'
                        """)
        public void checkNotifications(String username);
}
