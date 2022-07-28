package org.repository;

import org.config.DbConfig;
import org.entity.Article;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRepository {
    public static Article loadArticle(Article article, boolean checkIsPublished, boolean checkUser_id) throws SQLException {
        String query = """
                select * from article
                where id = ?\040
                """;
        query += checkIsPublished ? "and isPublished = true " : "";
        query += checkUser_id ? "and user_id = ?;" : ";";
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1, article.getId());
        if (checkUser_id)
            ps.setInt(2, article.getUser_id());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            setArticle(article, rs);
            article.setIsPublished(rs.getBoolean(6));
            return article;
        } else return null;
    }


    public static Article[] loadAllArticles() throws SQLException {
        Article[] article = new Article[100];
        String query = """
                select * from article
                where ispublished = true
                order by id;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        for (int i = 0; rs.next(); i++) {
            article[i] = new Article();
            setArticle(article[i], rs);
        }
        return article;
    }

    public static Article[] loadUserArticles(int user_id) throws SQLException {
        Article[] articleArray = new Article[100];
        String query = """
                select * from article
                where user_id = ?
                order by id;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        for (int i = 0; rs.next(); i++) {
            articleArray[i] = new Article();
            setArticle(articleArray[i], rs);
            articleArray[i].setIsPublished(rs.getBoolean(6));
        }
        if (articleArray[0] != null)
            return articleArray;
        else return null;

    }

    public static void editArticle(Article article) throws SQLException {
        String query = """
                update article
                set title = ?,
                brief = ?,
                content = ?,
                createdate = ?,
                ispublished = ?
                where id = ? and user_id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        setPreparedStatement(article, ps);
        ps.setBoolean(5, article.getIsPublished());
        ps.setInt(6, article.getId());
        ps.setInt(7, article.getUser_id());
        ps.execute();
    }

    public static void addArticle(Article article) throws SQLException {
        String query = """
                insert into article (title, brief, content, createdate, ispublished, user_id)
                values (?,?,?,?,?,?);
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        setPreparedStatement(article, ps);
        ps.setBoolean(5, article.getIsPublished());
        ps.setInt(6, article.getUser_id());
        ps.execute();
    }

    private static void setArticle(Article article, ResultSet rs) throws SQLException {
        article.setId(rs.getInt(1));
        article.setTitle(rs.getString(2));
        article.setBrief(rs.getString(3));
        article.setContent(rs.getString(4));
        article.setCreateDate(rs.getDate(5));
    }

    private static void setPreparedStatement(Article article, PreparedStatement ps) throws SQLException {
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getBrief());
        ps.setString(3, article.getContent());
        ps.setDate(4, (Date) article.getCreateDate());
    }
}
