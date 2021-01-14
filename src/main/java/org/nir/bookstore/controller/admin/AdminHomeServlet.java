package org.nir.bookstore.controller.admin; 

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Category;

/**
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("HELLO HOME PAGE");
		System.out.println(">>AdminHomeServlet: list of categories = " + request.getAttribute("categories"));
			
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	 @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}


		

}
