package com.example.instagram.Model.IdGenerators;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.sql.Connection;
import java.sql.Statement;
import com.example.instagram.Model.Post;

public class PostKeyGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object obj) {
        String prefix = ((Post)obj).getUsername() + ".";
        //check if this can be ignored 
        //JdbcConnectionAccess con = session.getJdbcConnectionAccess();

        try {
            Post post = (Post) obj;
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(CAST(SUBSTR(id,INSTR(id,'.') + 1)) AS BIGINT) FROM post where username = " + post.getUsername();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Long id = resultSet.getLong(1)+1;
                String generatedId = prefix + id;
                return generatedId;
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
