package fr.lubac.boutiqueSki.dal;

import java.util.List;

import fr.lubac.boutiqueSki.bo.Article;

public interface ArticleDAO {

    public Article selectById(int id);

    public List<Article> selectAll();

    public void update(Article article);

    public void insert(Article article);

    public void delete(int id);

}
