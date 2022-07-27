package org.repository;

import org.config.DbConfig;
import org.entity.Article;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRepository {
    public static Article showArticle(Article article) throws SQLException {
        String query = """
                select * from article
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1, article.getId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        article.setId(rs.getInt(1));
        article.setTitle(rs.getString(2));
        article.setBrief(rs.getString(3));
        article.setContent(rs.getString(4));
        article.setCreateDate(rs.getDate(5));
        article.setIsPublished(rs.getBoolean(6));
        return article;
    }
    public static Article showArticle(int id,int user_id) throws SQLException {
        Article article = new Article();
        String query = """
                select * from article
                where id = ? and user_id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setInt(1, id);
        ps.setInt(2,user_id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        article.setId(rs.getInt(1));
        article.setTitle(rs.getString(2));
        article.setBrief(rs.getString(3));
        article.setContent(rs.getString(4));
        article.setCreateDate(rs.getDate(5));
        article.setIsPublished(rs.getBoolean(6));
        return article;
    }

    public static Article[] loadAllAritcle() throws SQLException {
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
            article[i].setId(rs.getInt(1));
            article[i].setTitle(rs.getString(2));
            article[i].setBrief(rs.getString(3));
            article[i].setContent(rs.getString(4));
            article[i].setCreateDate(rs.getDate(5));
        }
        return article;
    }

    public static void editArticle(Article article) throws SQLException {
        String query = """
                update article
                set title = ?,
                brief = ?,
                content = ?,
                createdate = ?,
                ispublished = ?
                where id = ?;
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getBrief());
        ps.setString(3, article.getContent());
        ps.setDate(4, (Date) article.getCreateDate());
        ps.setBoolean(5, article.getIsPublished());
        ps.setInt(6, article.getId());
        ps.execute();
    }

    public static void addArticle(Article article) throws SQLException {
        String query = """
                insert into article (title, brief, content, createdate, ispublished, user_id)
                values (?,?,?,?,false,?);
                """;
        PreparedStatement ps = DbConfig.getConfig().prepareStatement(query);
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getBrief());
        ps.setString(3, article.getContent());
        ps.setDate(4, (Date) article.getCreateDate());
        ps.setInt(5, article.getUser_id());
        ps.execute();
    }
}
