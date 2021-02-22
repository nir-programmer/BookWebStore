package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.ArticleDAO;
import org.nir.bookstore.entities.Article;

public class ArticleService
{
	private HttpServletRequest request; 
	private HttpServletResponse response; 
	private ArticleDAO articleDAO;
	
	
	public ArticleService(HttpServletRequest request, HttpServletResponse response)
	{
	
		this.request = request;
		this.response = response;
		this.articleDAO  = new ArticleDAO();
	}

	public void listArticles() throws ServletException, IOException
	{
		String articlesPage = "articles_list.jsp";
		List<Article> articles ; 
		//open a session and transaction
		this.articleDAO.openCurrentSessionWithTransaction();
		
		//read list of articles
		articles = this.articleDAO.listAll();
		
		//commit transaction and close session
		this.articleDAO.closeCurrentSessionWithTransaction();
		
		//set the articles into the request
		request.setAttribute("articles", articles);
		
		
		//forward to articles_list.jspbook_form.jsp
		request.getRequestDispatcher("articles_list.jsp").forward(request, response);
		
	}

	public void newArticle() throws ServletException, IOException
	{
		String articleFormPage = "article_form.jsp"; 
		
		request.getRequestDispatcher(articleFormPage).forward(request, response);
		
	}
	
	
	
	
	
	
	
}
