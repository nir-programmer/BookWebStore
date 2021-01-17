package org.nir.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Book;

/**
 * Servlet implementation class ViewShoppingCart
 */
@WebServlet("/view_cart")
public class ViewShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String cartPage = "frontend/shopping_cart.jsp";
		
		Object cartObject = request.getSession().getAttribute("cart"); 
		
		if(cartObject == null)
		{
			ShoppingCart shoppingCart = new ShoppingCart(); 
			request.getSession().setAttribute("cart", shoppingCart);
		}
	
		
		//add books to the cart for testing!
		Book book = new Book() ;
		book.setTitle("Effective Java (3rd edition)");
		book.setPrice(20);
		
		//fetch the shopping cart attr from the session and put the add the book to the cart
		ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute("cart");
		shoppingCart.addItem(book);
		
		//forward to the cartPage
		request.getRequestDispatcher(cartPage).forward(request, response);
	}

}
