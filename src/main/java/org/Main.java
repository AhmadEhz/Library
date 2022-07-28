package org;

import org.entity.Article;
import org.entity.User;
import org.service.*;

import java.sql.Date;

public class Main {
    static Boolean exit = false;//for go back to previous menu.
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
                case "2" -> articlesMenu();
                case "0" -> exit = true;
                default -> {
                    Print.invalidEntry();
                    Utility.delay(1);
                }
            }
        }
    }

    //prints all published articles and asks the user which article to show.
    private static void articlesMenu() {
        Print.allArticles();
        while (!exit) {
            Print.articleMenu();
            String input = Input.scanner();
            if (input.equals("0"))
                exit = true;
            else {
                if (Utility.checkSelectedArticle(input, article, true, false))
                    selectedArticle(article);
            }
        }
        article = new Article();
        exit = false;
    }

    //takes the username from user, or goes to signUpMenu.
    private static void signInMenu() {
        while (!exit) {
            Print.signInMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> signUpMenu();
                case "0" -> exit = true;
                default -> {
                    user.setUsername(input);
                    if (Utility.isUsernameExist()) {
                        enterPasswordMenu();
                        exit = true;
                    }
                }
            }
        }
        user = new User();
        exit = false;
    }

    //After username taken, takes the password and checks the password is correct or not.
    private static void enterPasswordMenu() {
        while (!exit) {
            Print.enterPassword();
            String input = Input.scanner();
            if ("0".equals(input)) {
                exit = true;
            } else {
                user.setPassword(input);
                if (Utility.checkPassword()) {
                    Print.loggedIn();
                    Utility.delay(2);
                    userMenu();
                    exit = true;
                }
            }
        }
        user.setPassword(null);
        exit = false;
    }

    //After user login, this method is executed.
    private static void userMenu() {
        article.setUser_id(user.getId());
        while (!exit) {
            Print.userMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> userArticlesMenu();
                case "2" -> addArticleMenu();
                case "3" -> changePasswordMenu();
                case "0" -> exit = true;
                default -> {
                    Print.invalidEntry();
                    Utility.delay(1);
                    Print.enterNumber();
                }
            }
        }
        user = new User();
        exit = false;
    }


    //if user want to sign up, this method is executed and checks the username is not exist.
    private static void signUpMenu() {
        while (!exit) {
            Print.signUpMenu();
            String input = Input.scanner();
            if ("0".equals(input)) {
                exit = true;
            } else {
                user.setUsername(input);
                if (Utility.checkUsername(input)) {
                    createUserMenu();
                    exit = true;
                }
            }
        }
        user = new User();
        exit = false;
    }

    //after get username in signUpMenu, the user information is taken.
    private static void createUserMenu() {
        Print.enterNationalCode();
        user.setNationalCode(Input.scanner());
        Print.enterBirthday();
        user.setBirthday(Date.valueOf(Input.scanner()));
        UserService.createUser(user);
        Print.created();
    }

    //if user want to change password, this method is executed.
    private static void changePasswordMenu() {
        while (!exit) {
            Print.enterNewPassword();
            String input = Input.scanner();
            if (input.equals("0"))
                exit = true;
            else if (Input.checkPassword(input)) {
                user.setPassword(input);
                UserService.changePassword(user);
                exit = true;
                Print.passwordChanged();
            }
        }
        exit = false;
    }

    //Shows all article of user and ask the user which article want to edit.
    public static void userArticlesMenu() {
        Print.userArticles(user.getId());
        while (!exit) {
            Print.selectUserArticle();
            String input = Input.scanner();
            if (input.equals("0"))
                exit = true;
            else if (Utility.checkSelectedArticle(input, article, false, true)) {
                article.setId(Integer.parseInt(input));
                editArticleMenu(article);
            }
        }
        exit = false;
    }

    //If user select an article, this method is executed
    private static void editArticleMenu(Article article) {
        while (!exit) {
            ArticleService.loadSelectedArticle(article, false, true);
            Print.userArticleMenu();
            String input = Input.scanner();
            switch (input) {
                case "1" -> Print.article(article, true);
                case "2" -> Utility.changeArticleStatus();
                case "3" -> Utility.editArticle("title");
                case "4" -> Utility.editArticle("brief");
                case "5" -> Utility.editArticle("created date");
                case "6" -> Utility.editArticle("content");
                case "0" -> exit = true;
                default -> {
                    Print.invalidEntry();
                    Utility.delay(1);
                }
            }
        }
        Utility.clearArticle();
    }

    //if user want to add an article, this method takes the article and add it.
    private static void addArticleMenu() {
        Print.enterArticleTitle();
        article.setTitle(Input.scanner());
        Print.enterArticleBrief();
        article.setBrief(Input.scanner());
        Print.enterArticleCreateDate();
        article.setCreateDate(Date.valueOf(Input.scanner()));
        Print.enterArticleContent();
        article.setContent(Input.scanner());
        ArticleService.saveArticle(article);
        Print.added();
        Utility.clearArticle();
    }

    //After checks article id that entered, that article is selected and printed with this method.
    private static void selectedArticle(Article article) {
        article = ArticleService.loadSelectedArticle(article, true, false);
        Print.article(article, true);
        Utility.delay(2);
    }
}