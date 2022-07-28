package org.service;

import org.entity.User;
import org.repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    public static boolean checkPassword(User user) {
        return false;
    }

    public static boolean isExistUsername(User user, boolean checkPassword) {
        try {
            if (UserRepository.checkUser(user, checkPassword)) {
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createUser(User user) {
        try {
            UserRepository.saveUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void changePassword(User user) {
        try {
            UserRepository.changePassword(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
