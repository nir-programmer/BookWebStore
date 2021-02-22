package org.nir.bookstore.controller.admin.articles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.ArticleService;


@WebServlet("/admin/new_article")
public class ShowNewFormArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowNewFormArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		ArticleService articleService = new ArticleService(request, response);
		articleService.newArticle(); 
		
	}

}
