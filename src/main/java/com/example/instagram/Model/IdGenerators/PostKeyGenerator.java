package com.example.instagram.Model.IdGenerators;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.sql.Connection;
import java.sql.Statement;
import com.example.instagram.Model.Post;
import com.example.instagram.Model.KeyClass.PostKey;

public class PostKeyGenerator implements IdentifierGenerator {

    @Override
    public synchronized PostKey generate(SharedSessionContractImplementor session, Object obj) {

        try {
            Post post = (Post) obj;
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(id) FROM post where username = '"+  post.getKey().getUsername() + "'";
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
            PostKey generatedId = new PostKey();
            generatedId.setUsername(((Post) obj).getKey().getUsername());
            generatedId.setId(id);
            return generatedId;            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
