package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerLogoutServlet
 */
@WebServlet("/logout")
public class CustomerLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//remove  the loggedCustomer attribute from the session
		request.getSession().removeAttribute("loggedCustomer");
		
		//forward to the login.jsp page
		//request.getRequestDispatcher("frontend/login.jsp").forward(request, response);
		
		//redirect to the home page
		response.sendRedirect(request.getContextPath());
	}

}
