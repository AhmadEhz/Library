package org;

import org.entity.Article;
import org.entity.User;
import org.service.*;

import java.sql.Date;

public class Main {
    static Boolean exit = false;
    static Article article = new Article();
    static User user = new User();

    public static void main(String[] args) {
        Print.welcome();
        mainMenu();
    }

    private static void mainMenu() {
        while (!exit) {
            Print.mainMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> signInMenu();
                case "2" -> articleMenu();
                case "0" -> exit = true;
                default -> {
                    Print.invalidEntry();
                    Utility.delay(1);
                    Print.enterNumber();
                }
            }
        }
    }

    private static void articleMenu() {
        Print.allArticles();
        while (!exit) {
            Print.articleMenu();
            String input = Input.scanner();
            if (input.equals("0"))
                exit = true;
            else
                if (Utility.checkSelectedArticle(input))
                    selectedArticle(Integer.parseInt(input));
        }
        exit = false;
    }

    private static void selectedArticle(int id) {
        article.setId(id);
        Print.article(article);
        Utility.delay(2);
    }

    private static void signInMenu() {
        while (!exit) {
            Print.signInMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> signUpMenu();
                case "0" -> exit = true;
                default -> {
                    user.setUsername(input);
                    if (UserService.checkSignIn(user, false)) {
                        enterPasswordMenu();
                        exit = true;
                    } else {
                        Print.invalidUsername();
                        Utility.delay(1);
                    }
                }
            }
        }
        user = new User();
        exit = false;
    }

    private static void enterPasswordMenu() {
        while (!exit) {
            Print.enterPassword();
            String input = Input.scanner();
            switch (input) {
                case "0" -> exit = true;
                default -> {
                    user.setPassword(input);
                    if (UserService.checkSignIn(user, true))
                        userMenu();
                    else {
                        Print.notCorrectPassword();
                        Utility.delay(1);
                    }
                }
            }
        }
        user.setPassword(null);
        exit = false;
    }

    private static void userMenu() {
        while (!exit) {
            Print.userMenu();
            String input = Input.scanner();
            switch (input) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    Print.invalidEntry();
                    Utility.delay(1);
                    Print.enterNumber();
                    break;
            }
        }
        user = new User();
        exit = false;
    }

    private static void signUpMenu() {
        while (!exit) {
            Print.signUpMenu();
            String input = Input.scanner();
            switch (input) {
                case "0" -> exit = true;
                default -> {
                    user.setUsername(input);
                    if (!UserService.checkSignIn(user, false)) {
                        createUserMenu();
                        exit = true;
                    } else {
                        Print.usernameIsUsed();
                        Utility.delay(1);
                    }
                }
            }
        }
        user = new User();
        exit = false;
    }

    private static void createUserMenu() {
        Print.enterNationalCode();
        user.setNationalCode(Input.scanner());
        Print.enterBirthday();
        user.setBirthday(Date.valueOf(Input.scanner()));
        UserService.createUser(user);
        Print.createdUser();
        Utility.delay(2);
    }
}