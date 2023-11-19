package com.example.instagram.Model.IdGenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.example.instagram.Model.Comment;
import com.example.instagram.Model.KeyClass.CommentKey;

public class CommentKeyGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object obj) {
        try {
            Comment comment = (Comment) obj;
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(id) FROM comment where username = '" + comment.getKey().getUsername() + "'";
            ResultSet resultSet = statement.executeQuery(query);

            Long id;
            if (resultSet.next()) {
                id = resultSet.getLong(1) + 1;
            } else {
                id = (long) 1;
            }
            resultSet.close();
            statement.close();
            connection.close();
            CommentKey key = new CommentKey();
            key.setId(id);
            key.setUsername(comment.getKey().getUsername());
            System.out.println("key values");
            System.out.println(key.getUsername());
            System.out.println(key.getId());
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
