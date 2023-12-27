package com.example.instagram.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.instagram.Model.Post;
import com.example.instagram.Model.KeyClass.PostKey;

@Repository
public interface PostRepository extends JpaRepository<Post, PostKey> {

    @Query(nativeQuery = true, value = "SELECT MAX(CAST((SUBSTR(id, INSTR(id, '.') + 1)) AS UNSIGNED) + 1) FROM post WHERE username = ?1")
    public Long getMaxPostId(String username);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM   post
            WHERE username = ?1
            """)
    public Set<Post> findPostsForUser(String username);
}
