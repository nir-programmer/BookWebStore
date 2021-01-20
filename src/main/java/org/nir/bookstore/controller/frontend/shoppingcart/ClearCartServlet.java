package org.nir.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClearCartServlet
 */
@WebServlet("/clear_cart")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ClearCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ShoppingCart shoppingCart ; 
		String cartPage; 
		
		
		//get the ShoppingCart object from the session 
		shoppingCart = (ShoppingCart)request.getSession().getAttribute("cart"); 
		
		//clear the cart
		shoppingCart.clear();
		
		//REDIRECT to the shopping_cart.jsp page
		cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
