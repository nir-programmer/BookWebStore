package org.nir.bookstore.controller.admin.categories;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CategoriesService;

/**
 * Servlet implementation class CreateCategoryServlet
 */
@WebServlet("/admin/create_categories")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CategoriesService categoriesService = new CategoriesService(request, response); 
		categoriesService.createCategory() ;
	}

}
