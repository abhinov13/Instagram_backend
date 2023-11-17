package com.example.instagram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.Model.Comment;
import com.example.instagram.Model.KeyClass.CommentKey;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentKey>{
    
}
