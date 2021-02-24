package org.nir.bookstore.controller.admin.articles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.ArticleService;

@WebServlet("/admin/delete_article")
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		
		ArticleService articleService = new ArticleService(request, response);
		articleService.deleteArticle();
				
	}

}
