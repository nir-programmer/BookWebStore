package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.OrderDAO;
import org.nir.bookstore.entities.BookOrder;

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
	
	

	

}
