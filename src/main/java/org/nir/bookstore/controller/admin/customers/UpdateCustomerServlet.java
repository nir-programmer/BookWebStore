package org.nir.bookstore.controller.admin.customers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
@WebServlet("/admin/update_customer")
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		CustomerService customerService= new CustomerService(request, response);
		customerService.updateCustomer();
		
		
		//retrieve 2 customers from db - by email and by id
		
		//check if the 
	}

}
