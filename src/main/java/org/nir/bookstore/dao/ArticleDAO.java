package org.nir.bookstore.dao;

import java.util.List;

import org.nir.bookstore.entities.Article;

public class ArticleDAO extends HibernateDAO<Article> implements GenericeDAO<Article>
{

	@Override
	public Article create(Article article)
	{
		return super.create(article);
	}
	
	@Override
	public Article get(Object id)
	{
		return super.find(Article.class, id);
	}

	
	@Override
	public Article update(Article article)
	{
		return super.update(article);
	}
	@Override
	public void delete(Object id)
	{
		super.delete(Article.class, id);
		
	}

	@Override
	public List<Article> listAll()
	{
		return super.findWithNamedQuery("Article.findAll" ,0 ,4); 
		
	}

	@Override
	public long count()
	{
		return super.countWithNamedQuery("Article.CountAll"); 
	}

}
