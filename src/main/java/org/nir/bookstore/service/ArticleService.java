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
		listArticles(null);
		
	}
	
	private void listArticles(String message) throws ServletException, IOException
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
		
		//set the message into the request
		if(message != null)
			request.setAttribute("message", message);
		
		//forward to articles_list.jspbook_form.jsp
		request.getRequestDispatcher("articles_list.jsp").forward(request, response);
		
		
	}
	

	public void newArticle() throws ServletException, IOException
	{
		String articleFormPage = "article_form.jsp"; 
		
		request.getRequestDispatcher(articleFormPage).forward(request, response);
		
	}

	public void createArticle() throws IOException, ServletException
	{
		String title ; 
		String content; 
		Article article ; 
		String message;
		//1.Read the form input :title , content:OK
		title = request.getParameter("title");
		content = request.getParameter("content");
		
		//response.getWriter().println(">>createArticle(): title = " + title + " \ncontent = " + content);
		
		//2.Create a new Article Object from these values
		article = new Article(title, content);
		
		//3.Persist the object to the database: OK!!!
		this.articleDAO.openCurrentSessionWithTransaction();
		Article savedArticle = this.articleDAO.create(article);
		this.articleDAO.closeCurrentSessionWithTransaction();
		
		//response.getWriter().println(">>createArticle(): The article: " + savedArticle + " has been saved!");

		//4. Call listArticles() method with a success message parameter
		message = "A new article has been saved successfully!"; 
		this.listArticles(message);
		
	}

	
	
	
	
	
	
	
}
