package org.repository;

import org.config.DbConfig;
import org.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public void signUp(User user) throws SQLException {

        String query = """
                insert into users (username,national_code,birthday,password)
                values (?,?,?,?)""";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getNationalCode());
        ps.setString(3, user.getBirthday());
        ps.setString(4, user.getPassword());
        ps.executeUpdate();
        ResultSet generatedIds = ps.getGeneratedKeys();
        generatedIds.next();
        user.setId(generatedIds.getInt(1));
        ps.close();
    }

    public void changePassword(User user) throws SQLException {
        String query = """
                update users 
                set password = ?
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, user.getPassword());
        ps.setInt(2, user.getId());
        ps.execute();
    }

    public boolean signIn(User user) throws SQLException {
        String query = """
                select id, username , password from users
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, user.getId());
        ResultSet resultSet = ps.executeQuery();
        return resultSet.getString(1).equals(user.getUsername()) && resultSet.getString(2).equals(user.getPassword());
    }
}
