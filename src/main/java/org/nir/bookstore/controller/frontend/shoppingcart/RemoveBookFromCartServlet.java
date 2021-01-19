package org.nir.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Book;


@WebServlet("/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RemoveBookFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Integer bookId ; 
		Object cartObject ; 
		ShoppingCart shoppingCart;
		String cartPage; 
		
		//fetch the bookId (String type)from the request
		bookId = Integer.parseInt(request.getParameter("book_id")); 
		
		cartObject = request.getSession().getAttribute("cart"); 
		
		//Assume THERE IS a "cart" attribute in the session !!unlike the adding feature
		shoppingCart = (ShoppingCart)cartObject;
		
		//remove the book form the cart
		shoppingCart.removeItem(new Book(bookId));		
		
		
		//REDIRECT to the shopping_cart.jsp 
		cartPage = request.getContextPath().concat("/view_cart"); 
		response.sendRedirect(cartPage);
		
		
		
	}

}
