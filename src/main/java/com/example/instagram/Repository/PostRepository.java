package com.example.instagram.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.instagram.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    
    @Query(nativeQuery = true ,value = "SELECT MAX(CAST((SUBSTR(id, INSTR(id, '.') + 1)) AS UNSIGNED) + 1) FROM post WHERE username = ?1 AND creation_date = (SELECT MAX(creation_date) FROM post WHERE username = ?1)")
    public Long getMaxPostId(String username);
}
