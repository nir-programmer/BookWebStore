package org.nir.bookstore.controller.admin.orders;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.service.CommonUtitlity;

@WebServlet("/admin/remove_book_from_order")
public class RemoveBookFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RemoveBookFromOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int bookId; 
		HttpSession session; 
		BookOrder bookOrder; 
		Set<OrderDetail> orderDetails; 
		Iterator<OrderDetail> iterator; 
		String editOrderFormPage ; 
		
		
		
		//1.Read the id of the book from the request(comes from the drop down list of OrderDetails)
		bookId  = Integer.parseInt(request.getParameter("id"));
		
		//2.Read the BookOrder object from the SESSION
		session = request.getSession();
		bookOrder = (BookOrder) session.getAttribute("order"); 
		
		//3.Loop over the OrderDetails(Set)  with iterator 
		orderDetails = bookOrder.getOrderDetails();
		iterator = orderDetails.iterator();
		
		while(iterator.hasNext())
		{
			OrderDetail orderDetail = iterator.next();
			
			//4.If there is orderDetail.book.getBookId() == id then remove it
			if(orderDetail.getBook().getBookId() == bookId)
			{
				//NO NEED!
				//4.1 Read the "quantity" from the request into quantity
				//float quantity = Float.parseFloat(request.getParameter("quantity"));
				
				//4.2 Read the product book.price() * quantity into subtotal varialbe - WRONG
				//float subtotal = orderDetail.getBook().getPrice() * quantity;
				
				//4.2 Read the order.total - orderDetail.subtotal into order.total
				float newTotal = bookOrder.getTotal() - orderDetail.getSubtotal();
				bookOrder.setTotal(newTotal); 
		  		 
		  		//4.4remove the orderDetail form the set(by using the iterator
				iterator.remove();
			}
		}
		
		//5.Forward the request to order_form.jsp 
		editOrderFormPage = "order_form.jsp";
		CommonUtitlity.forwardToPage(editOrderFormPage, request, response);
		
		
		
		
	}

}
