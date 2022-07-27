package org.service;

import org.entity.Article;
import org.repository.ArticleRepository;

import java.sql.SQLException;

public class ArticleService {
    static Article article = new Article();

    public static boolean checkArticle(Article article) {
        try {
            if (ArticleRepository.showArticle(article) != null)
                return true;
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Article[] loadAllArticles() {
        try {
            return ArticleRepository.loadAllAritcle();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Article loadSelectedArticle(Article article) {
        try {
            return ArticleRepository.showArticle(article);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
