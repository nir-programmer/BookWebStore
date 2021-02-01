package org.nir.bookstore.controller.admin.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.service.CommonUtitlity;


@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddBookToOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Integer bookId;
		int quantity; 
		BookDAO bookDAO;
		Book book; 
		HttpSession session;
		BookOrder bookOrder; 
		float subtotal;
		OrderDetail orderDetail;
		float newTotal; 
		String resultPage;
		//important..
		String newBookPendingToAddToOrder = "newBookPendingToAddToOrder";
		
		//1.Read from the request the attribute "bookId":
		bookId = Integer.parseInt(request.getParameter("bookId"));
		
		//2.Read from the request the attribute "quantity".
		quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//3.Read the Book object with this id from the database.
		bookDAO = new BookDAO();
		bookDAO.openCurrentSession();
		book = bookDAO.get(bookId);
		bookDAO.closeCurrentSession();
		
		//4.Add this Book object to the existing BookOrder in the session
		session = request.getSession();
		bookOrder = (BookOrder)session.getAttribute("order");
		
		
		//5.Re-calculate order total and update the order
		subtotal = book.getPrice() * quantity;
		
		//6.Create an OrderDetail object and set the subtotal , orderId , bookId, quantity
		orderDetail = new OrderDetail(); 
		orderDetail.setBook(book);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subtotal);
		//orderDetail.setBookOrder(bookOrder);
		
		
		//7.Calculate the new total price for the order and set this value in the bookOrder
		newTotal = bookOrder.getTotal() + subtotal;
		bookOrder.setTotal(newTotal); 
		
		
		//8.Add the orderDetail to this bookOrder's orderDetails (Set<OrderDetail>)
		bookOrder.getOrderDetails().add(orderDetail);
		
		//IMPORTANT: 
		//9.Add the book to the SESSION- since I will use this book later in the second popup window page
		 //request.setAttribute("book", book);
		request.setAttribute("book", book);
		
		//IMPORTANT: add this to enforce the OrderService to take the book in the SESSION and not from db
		 session.setAttribute("NewBookPendingToAddToOrder", true);
		 
		//10.Forward the request to a page that display a successfull message
		resultPage = "add_book_result.jsp";
		CommonUtitlity.forwardToPage(resultPage, request, response);
	}

}
