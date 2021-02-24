package org.nir.bookstore.controller.frontend.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.ArticleDAO;
import org.nir.bookstore.entities.Article;

@WebServlet("/view_article")
public class ViewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		Integer articleId = Integer.parseInt(request.getParameter("id")) ;
		Article article ; 
		ArticleDAO articleDAO;
		String message; 
		
		// Read the id of the article from the request
		articleDAO = new ArticleDAO();
		articleDAO.openCurrentSessionWithTransaction();
		
		//read the article from the db
		article = articleDAO.get(articleId);
		
		//OK: I get the article form the db!
		//response.getWriter().println(">>article:\n" + article);
		
		System.out.println("\n\n>>ViewArticleServlet.doGet():The article form the db: \n" +article);
		//check if exists
		if(article == null)
		{
			articleDAO.closeCurrentSessionWithTransaction();
			message = "Sorry the article does not exists!"; 
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return; 
		
		}
		
		articleDAO.closeCurrentSessionWithTransaction();
	
		// set this article into the request
		request.setAttribute("article", article);
		
		// forward to the article_details.jsp page
		request.getRequestDispatcher("/frontend/article_details.jsp").forward(request, response);

	}

}
