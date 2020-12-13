package org.nir.bookstore.controller.admin.categories;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CategoriesService;

/**
 * Servlet implementation class LIstCategoriesServlet
 */
@WebServlet("/admin/list_categories")
public class LIstCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LIstCategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		CategoriesService categoriesService = new CategoriesService(request, response); 
		
		categoriesService.listAll();
	}

	
}
