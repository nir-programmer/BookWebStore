package org.nir.bookstore.controller.admin.customers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;

/**
 * Servlet implementation class CreateCustomerServlet
 */
@WebServlet("/admin/create_customer")
public class CreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CreateCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CustomerService customerService = new CustomerService(request, response);
		customerService.createCustomer(); 
	}

}
