package org.nir.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
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
		String cartPage ;
		
		Object cartObject ;
		
		ShoppingCart shoppingCart;
		
		
		
		
		cartObject = request.getSession().getAttribute("cart"); 
		
		
		if(cartObject == null)
		{
			 shoppingCart = new ShoppingCart(); 
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		 shoppingCart = (ShoppingCart)request.getSession().getAttribute("cart");
		
		 cartPage = "frontend/shopping_cart.jsp";
		 
		 request.getRequestDispatcher(cartPage).forward(request, response);
		//add IM MEMORY books to the cart for testing!
		/*
		 * Book book = new Book() ; book.setTitle("Effective Java (3rd edition)");
		 * book.setPrice(20);
		 */
		
		//add PERSISTED book from the data base to the cart for testing!
		/*
		 * BookDAO bookDAO = new BookDAO(); bookDAO.openCurrentSession(); Book
		 * persistedBook1 = bookDAO.get(56); Book persistedBook2 = bookDAO.get(43); Book
		 * persistedBook3 = bookDAO.get(46); bookDAO.closeCurrentSession();
		 */
		
		//fetch the shopping cart attr from the session and put the add the book to the cart
		
		/*
		 * shoppingCart.addItem(persistedBook1); shoppingCart.addItem(persistedBook2);
		 * shoppingCart.addItem(persistedBook2); shoppingCart.addItem(persistedBook2);
		 * shoppingCart.addItem(persistedBook3);
		 */
		
		//forward to the cartPage
		
	}

}
