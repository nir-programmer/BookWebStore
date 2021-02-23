package org.nir.bookstore.controller.admin.articles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Article;
import org.nir.bookstore.service.ArticleService;


@WebServlet("/admin/update_article")
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		ArticleService articleService  = new ArticleService(request, response);
		articleService.updateArticle(); 
	}

}
