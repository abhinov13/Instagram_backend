package com.example.instagram.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.instagram.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    
    @Query("SELECT id FROM post WHERE username = ?1 AND creationDate = (SELECT MAX(creationDate) FROM post WHERE username = ?1)")
    public Long getMaxPostId(String username);
}
