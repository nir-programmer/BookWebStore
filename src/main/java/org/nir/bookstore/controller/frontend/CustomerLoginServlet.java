package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CustomerLoginServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CustomerService customerService = new CustomerService(request, response);
		customerService.showLogin() ; 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		CustomerService customerService = new CustomerService(request, response);
		customerService.doLogin();
		
		
		
		
	}

}