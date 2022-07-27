package org;


import org.service.ArticleService;

import java.util.concurrent.TimeUnit;

import static org.Main.article;

class Utility {
    static void delay(int delayTime) {
        try {
            TimeUnit.SECONDS.sleep(delayTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static boolean checkSelectedArticle(String input) {
        if (Input.checkInput(input)) {
            article.setId(Integer.parseInt(input));
            if (ArticleService.checkArticle(article)) {
                return true;
            } else {
                Print.notValidArticle();
                Utility.delay(1);
                return false;
            }
        } else {
            Print.invalidEntry();
            Utility.delay(1);
            return false;
        }
    }

}
