package org;

import org.entity.Article;
import org.service.ArticleService;

import static org.Main.article;

public class Print {

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
                1- Show / edit your articles
                2- Add article
                3- Change password
                0- Log out""");
        enterNumber();
    }

    static void userArticleMenu() {
        System.out.println("1- Show article" +
                "\n2- Change to " + articleStatus(!article.getIsPublished()) +
                "\n3- Edit title of article" +
                "\n4- Edit brief of article" +
                "\n5- Edit created date of article" +
                "\n6- Edit content of article" +
                "\n0- Exit");
        Print.enterNumber();
    }

    static void allArticles() {
        Article[] articleArray = ArticleService.loadAllArticles();
        for (int i = 0; articleArray[i] != null; i++)
            article(articleArray[i], false);
        System.out.println("--------------------END--------------------");
        Utility.delay(1);
    }

    static void enterPassword() {
        System.out.println("Enter your password(0 to exit):");
    }

    static void notCorrectPassword() {
        System.out.println("Not correct!");
    }

    static void articleMenu() {
        System.out.print("To show an article, enter id of article (Enter 0 to exit): ");
    }

    public static void notValidArticle() {
        System.out.println("This article not found!");
    }

    public static void article(Article article, boolean printTitle) {
        if (printTitle)
            System.out.println(article);
        else System.out.println(article.toStringSummary());
    }

    public static void signUpMenu() {
        System.out.println("Enter your Username to sign up (0 to exit):");
    }

    public static void usernameIsNotExist() {
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

    public static void created() {
        System.out.println("Created!");
        Utility.delay(2);
        System.out.println("Your default password is your national code.");
        Utility.delay(2);
    }

    static void loggedIn() {
        System.out.println("Logged in!");
    }

    public static void userArticles(int user_id) {
        Article[] articleArray = ArticleService.loadUserArticles(user_id);
        if (articleArray[0] != null)
            for (int i = 0; articleArray[i] != null; i++) {
                article(articleArray[i], false);
                System.out.println("Status: " + articleStatus(articleArray[i].getIsPublished()));
            }
        Utility.delay(2);
    }

    public static String articleStatus(boolean articleStatus) {
        return articleStatus ? "Published" : "Private";
    }

    public static void selectUserArticle() {
        System.out.print("To select an article, enter id (0 to exit): ");
    }

    static void enterArticleTitle() {
        System.out.println("Enter title of your article:");
    }

    static void enterArticleBrief() {
        System.out.println("Enter brief of your article:");
    }

    static void enterArticleCreateDate() {
        System.out.println("Enter created date of your article (format:yyyy-mm-dd) :");
    }

    static void enterArticleContent() {
        System.out.println("Enter content of your article:");
    }

    public static void added() {
        System.out.println("Added!");
        Utility.delay(2);
    }

    public static void enterNewPassword() {
        System.out.println("Enter new password (0 to exit) :");
    }

    public static void passwordChanged() {
        System.out.println("Password changed!");
        Utility.delay(2);
    }

    static void invalidPassword() {
        System.out.println("The length of the password must be at least 8 characters!");
        Utility.delay(2);
    }

    public static void errorLengthUsername() {
        System.out.println("The length of the username must be at least 5 characters!");
    }

    public static void editArticle(String toEdited) {
        if (toEdited.equals("created date"))
            System.out.println("Enter created date (format: yyyy-mm-dd) :");
        System.out.println("Enter " + toEdited + ":");
    }

    public static void changed() {
        System.out.println("Changed!");
    }
}
