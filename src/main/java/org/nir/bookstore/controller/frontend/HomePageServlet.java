package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.service.CategoriesService;

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("")
public class HomePageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.openCurrentSessionWithTransaction();

		List<Category> categories = categoryDAO.listAll();

		categoryDAO.closeCurrentSessionWithTransaction();

		request.setAttribute("categories", categories);
		request.getRequestDispatcher("frontend/index.jsp").forward(request, response);
	}

	

}
