package org;

import org.entity.Article;
import org.service.ArticleService;

public class Print {
    static Article article = new Article();
    static void welcome() {
        System.out.println("Welcome!");
    }

    static void enterNumber() {
        System.out.print("Enter a Number: ");
    }

    static void invalidEntry() {
        System.out.println("Invalid Entry!");
    }

    static void mainMenu() {
        System.out.println("""
                1- Sign in / Sign up
                2- Show all articles
                0- Exit""");
        enterNumber();
    }

    static void signInMenu() {
        System.out.println("Enter your username to sign in (Enter 1 for sign up, 0 for exit):");
    }

    static void userMenu() {
        System.out.println("""
                1- Show all your articles
                2- Add article
                3- Change password
                0- Log out""");
        enterNumber();
    }

    static void allArticles() {
        Article[] articleArray = ArticleService.loadAllArticles();
        for (int i = 0; articleArray[i] != null; i++)
            System.out.print(articleArray[i]);
        System.out.println("--------------------END--------------------");
        articleMenu();
    }

    static void enterPassword() {
        System.out.println("Enter your password:");
    }

    static void notCorrectPassword() {
        System.out.println("Not correct!");
    }
    static void articleMenu() {
        System.out.print("To show an article, enter id of article (Enter 0 to exit): ");
    }

    public static void notValidArticle() {
    }

    public static void article(Article article) {
       article = ArticleService.loadSelectedArticle(article);

    }
    private static void printArticle(boolean showTitle) {

    }

    public static void signUpMenu() {
        System.out.println("Enter your Username to sign up (0 to exit):");
    }

    public static void invalidUsername() {
        System.out.println("This Username is not exist!");
    }

    public static void usernameIsUsed() {
        System.out.println("This Username is used!");
    }

    public static void enterNationalCode() {
        System.out.println("Enter your national code: ");
    }

    public static void enterBirthday() {
        System.out.println("Enter your birthday (format yyyy-mm-dd):");
    }
    public static void createdUser() {
        System.out.println("Created!");
    }
}
