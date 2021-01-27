package org.nir.bookstore.controller.admin.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.OrderService;


@WebServlet("/admin/view_order")
public class ViewOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewOrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		OrderService orderService = new OrderService(request, response);
		orderService.viewOrderDetailForAdmin();
		
		/*
		 * String orderDetailPage = "order_details.jsp";
		 * 
		 * request.getRequestDispatcher(orderDetailPage).forward(request, response);
		 */
	}

}
