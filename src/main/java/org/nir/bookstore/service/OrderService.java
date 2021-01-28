package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.OrderDAO;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.OrderDetail;

public class OrderService
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDAO orderDAO; 
	
	public OrderService(HttpServletRequest request, HttpServletResponse response)
	{
		super();
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO() ;
	}


	public void listAll() throws ServletException, IOException
	{
		String ordersPage = "orders_list.jsp"; 
		//OrderDAO orderDAO;
		List<BookOrder> bookOrders; 
		
		//1.read all orders from the data base into List  orders
		//orderDAO = new OrderDAO() ; 
		orderDAO.openCurrentSession();
		bookOrders = orderDAO.listAll();
		orderDAO.closeCurrentSession();
		
		//2.set the list into the request attriubute
		request.setAttribute("orders", bookOrders) ;

		
		//3.forward the request to the orders page
		CommonUtitlity.forwardToPage(ordersPage, request, response);
	}
	
	public void  viewOrderDetailForAdmin() throws ServletException, IOException
	{
		String orderDetailPage = "order_details.jsp";
		Integer id = Integer.parseInt(request.getParameter("id")); 
		System.out.println(">>OrderService.viewOrderDetailForAdmin(): id of BookOrder is :" + id);
		//Just for testing a non existing order: id = 2
		orderDAO.openCurrentSession();
		//change to id!!just test
		BookOrder bookOrder = this.orderDAO.get(id); 
		orderDAO.closeCurrentSession();
		
		//Assignment 21:
		if(bookOrder == null)
		{
			String message = "Could not find order with ID 2";
			request.setAttribute("message", message);
			
		}
		else
		{
		System.out.println(">>OrderService.viewOrderDetailForAdmin(): List Of Books id's in the OrderDetails:");
		Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();
		
		orderDetails.forEach(d -> System.out.println(d.getBook().getBookId()));
		
		request.setAttribute("order", bookOrder);
		}
		
		request.getRequestDispatcher(orderDetailPage).forward(request, response);
		
		
		
	}


	public void showCheckOutForm() throws ServletException, IOException
	{
		String checkoutPage = "checkout";
		
		CommonUtitlity.forwardToPage(checkoutPage, request, response);
		
	}
	
	

	

}
