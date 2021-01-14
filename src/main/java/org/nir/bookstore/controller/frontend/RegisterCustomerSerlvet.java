package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;

/**
 * Servlet implementation class RegisterCustomerSerlvet
 */
@WebServlet("/register_customer")
public class RegisterCustomerSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterCustomerSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		CustomerService customerService = new CustomerService(request, response);
		customerService.registerCustomer();
	}

}
