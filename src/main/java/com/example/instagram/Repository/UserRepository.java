package com.example.instagram.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.instagram.Model.User;

public interface UserRepository extends JpaRepository<User, String> {
        @Query(nativeQuery = true, value = """
                        SELECT u.*
                        FROM   user u
                        INNER JOIN (SELECT   username,
                                             count(*) AS count
                                    FROM     followers
                                    WHERE    username != ?1
                                    AND      username NOT IN (SELECT follower_username
                                                              FROM   followers
                                                              WHERE  username = ?1)
                                    GROUP BY username) v_users
                        ON       v_users.username = u.username
                        ORDER BY count DESC
                        LIMIT 5
                            """)
        public Set<User> getMostPopularUsers(String username);

        @Query(nativeQuery = true, value = """
                        SELECT *
                        FROM user
                        WHERE name LIKE CONCAT(?1,'%')
                        """)
        public Set<User> searchUserByName(String name);

        @Query(nativeQuery = true, value = """
                        SELECT *
                        FROM user
                        WHERE username LIKE CONCAT(?1,'%')
                        """)
        public Set<User> searchUserByUsername(String name);

        @Query(nativeQuery = true, value = """
                        SELECT profile_picture_url
                        FROM   user
                        WHERE  username = ?1
                        """)
        public String getAvatarLink(String username);

}