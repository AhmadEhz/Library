package org;


import org.entity.Article;
import org.service.ArticleService;
import org.service.UserService;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import static org.Main.article;
import static org.Main.user;

class Utility {
    static void delay(int delayTime) {
        try {
            TimeUnit.SECONDS.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean checkSelectedArticle(String input, Article article, boolean checkIsPublished, boolean checkUser_id) {
        if (Input.checkInput(input)) {
            article.setId(Integer.parseInt(input));
            if (ArticleService.checkArticle(article, checkIsPublished, checkUser_id)) {
                return true;
            } else {
                Print.notValidArticle();
                Utility.delay(2);
                return false;
            }
        } else {
            Print.invalidEntry();
            Utility.delay(1);
            return false;
        }
    }

    static boolean isUsernameExist() {
        if (UserService.isExistUsername(user, false)) {
            return true;
        } else {
            Print.usernameIsNotExist();
            Utility.delay(1);
            return false;
        }
    }

    static boolean checkPassword() {
        if (UserService.isExistUsername(user, true))
            return true;
        else {
            Print.notCorrectPassword();
            Utility.delay(1);
            return false;
        }
    }

    static boolean checkUsername(String input) {
        if (Input.checkUsername(input)) {
            if (!UserService.isExistUsername(user, false))
                return true;
            else {
                Print.usernameIsUsed();
                Utility.delay(1);
                return false;
            }
        } else return false;

    }

    static void clearArticle() {
        article.setId(0);
        article.setTitle(null);
        article.setBrief(null);
        article.setContent(null);
        article.setCreateDate(null);
        article.setIsPublished(false);
    }

    static void editArticle(String toEditedPart) {
        Print.editArticle(toEditedPart);
        String input = Input.scanner();
        switch (toEditedPart) {
            case "title" -> article.setTitle(input);
            case "brief" -> article.setBrief(input);
            case "created date" -> article.setCreateDate(Date.valueOf(input));
            case "content" -> article.setContent(input);
        }
        ArticleService.editArticle(article);
        Print.changed();
        delay(2);
    }

    public static void changeArticleStatus() {
        article.setIsPublished(!article.getIsPublished());
        ArticleService.editArticle(article);
        Print.changed();
        delay(2);
    }
}
