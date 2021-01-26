package org.nir.bookstore.controller.admin.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.CommonUtitlity;
import org.nir.bookstore.service.OrderService;


@WebServlet("/admin/list_orders")
public class ListOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * String ordersPage = "orders_list.jsp";
		 * 
		 * CommonUtitlity.forwardToPage(ordersPage, request, response);
		 */
		
		OrderService orderService = new OrderService(request, response);
		orderService.listAll(); 
	}

	

}
