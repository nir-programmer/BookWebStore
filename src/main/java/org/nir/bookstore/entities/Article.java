package org.nir.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Integer articleId; 
	
	
	private String title ; 
	
	private String content;

	public Article() {}
	
	public Article(String title, String content)
	{
		super();
		this.title = title;
		this.content = content;
	}

	public Integer getArticleId()
	{
		return articleId;
	}

	public void setArticleId(Integer articleId)
	{
		this.articleId = articleId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
	
	@Override
	public String toString()
	{
		return "Article [articleId=" + articleId + ", title=" + title + ", content=" + content + "]";
	}

}
