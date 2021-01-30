package org.nir.bookstore.controller.admin.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.OrderService;

@WebServlet("/admin/edit_order")
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at:EditOrderServlet ").append(request.getContextPath());
		OrderService orderService = new OrderService(request, response);
		orderService.showEditOrderForm();
	}

}
