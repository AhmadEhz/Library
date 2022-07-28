package org.service;

import org.entity.Article;
import org.repository.ArticleRepository;

import java.sql.SQLException;

public class ArticleService {

    public static boolean checkArticle(Article article, boolean checkIsPublished, boolean checkUser_id) {
        try {
            return ArticleRepository.loadArticle(article, checkIsPublished, checkUser_id) != null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Article[] loadAllArticles() {
        try {
            return ArticleRepository.loadAllArticles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Article loadSelectedArticle(Article article, boolean checkIsPublished, boolean checkUser_id) {
        try {
            return ArticleRepository.loadArticle(article, checkIsPublished, checkUser_id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Article[] loadUserArticles(int user_id) {
        Article[] article;
        try {
            article = ArticleRepository.loadUserArticles(user_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    public static void saveArticle(Article article) {
        try {
            ArticleRepository.addArticle(article);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void editArticle(Article article) {
        try {
            ArticleRepository.editArticle(article);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
