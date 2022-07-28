package org.repository;

import org.config.DbConfig;
import org.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public static void saveUser(User user) throws SQLException {

        String query = """
                insert into users (username,national_code,birthday,password)
                values (?,?,?,?)""";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getNationalCode());
        ps.setDate(3, user.getBirthday());
        ps.setString(4, user.getNationalCode());
        ps.execute();
        ps.close();
    }

    public static void changePassword(User user) throws SQLException {
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

    public static boolean checkUser(User user, boolean checkPassword) throws SQLException {
        String query = " select id from users " +
                "where userName = ?";
        query += checkPassword ? " and password = ?;" : ";";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getUsername());
        if (checkPassword)
            ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user.setId(rs.getInt(1));
            return true;
        } else return false;
    }
}
