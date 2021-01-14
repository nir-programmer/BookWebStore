package org.nir.bookstore.controller.admin.customers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;

/**
 * Servlet implementation class ListCustomersServlet
 */
@WebServlet("/admin/list_customers")
public class ListCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListCustomersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		CustomerService customerService = new CustomerService(request , response);
		customerService.listCustomers();
	}

	

}
