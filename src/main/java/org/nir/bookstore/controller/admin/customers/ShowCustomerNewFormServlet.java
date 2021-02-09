package org.nir.bookstore.controller.admin.customers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CustomerService;


@WebServlet("/admin/new_customer")
public class ShowCustomerNewFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShowCustomerNewFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		CustomerService customerService = new CustomerService(request, response);
		customerService.newCustomer();
	}

}