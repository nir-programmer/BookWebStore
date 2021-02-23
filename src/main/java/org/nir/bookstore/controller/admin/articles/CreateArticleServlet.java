package org.nir.bookstore.controller.admin.articles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.ArticleService;

@WebServlet("/admin/create_article")
public class CreateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		//response.getWriter().println(">>CreateArticleServlet!!!");
		
		ArticleService articleService = new ArticleService(request, response);
		
		articleService.createArticle(); 
		
		
	}

}
