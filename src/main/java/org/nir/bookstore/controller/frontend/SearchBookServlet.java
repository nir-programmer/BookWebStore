package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/search")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*
		 * String keyword = request.getParameter("keyword"); // TODO Auto-generated
		 * method stub response.getWriter().print(keyword);
		 */
		
	}

}
