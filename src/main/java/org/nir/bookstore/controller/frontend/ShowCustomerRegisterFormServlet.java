package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;

/**
 * Servlet implementation class ShowCustomerRegisterFormServlet
 */
@WebServlet("/register")
public class ShowCustomerRegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ShowCustomerRegisterFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//WRONT URI
		//request.getRequestDispatcher("/register_form.jsp").forward(request, response);
		
		request.getRequestDispatcher("/frontend/customer_registration.jsp").forward(request, response);
	}

}
