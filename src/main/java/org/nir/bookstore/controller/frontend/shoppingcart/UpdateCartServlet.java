package org.nir.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.BookService;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCartServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String[] arrayBookIds;
		String[] arrayQuantities;
		
		int [] bookIds ; 
		int [] quantities;
		
		ShoppingCart cart ; 
		
		String cartPage = "frontend/shopping_cart.jsp"; 

		// These are static names in the page - just copy the array of parameters from
		// the request
		arrayBookIds = request.getParameterValues("book_id");

		// These are DYNAMIC names in the page - build them DYNIMCALLY
		arrayQuantities = new String[arrayBookIds.length];

		for (int i = 1; i <= arrayBookIds.length; i++) {
			String aQuantity = request.getParameter("quantity" + i);
			arrayQuantities[i - 1] = aQuantity;
		}
		
		
		//JAVA 8 : Lambda expressions
		bookIds = Arrays.stream(arrayBookIds).mapToInt(Integer::parseInt).toArray(); 
		quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();

		//Update the shopping cart:
		cart = new ShoppingCart(); 
		cart.updateCart(bookIds, quantities);
		
		
		//fetch the ShoppingCart object from the session - the "cart" attribute 
		cart = (ShoppingCart)request.getSession().getAttribute("cart") ;
		

		//update the shopping cart
		cart.updateCart(bookIds, quantities);
		
		
		//REDIRECT to the shopping_cart.jsp page 
		cartPage = request.getContextPath().concat("/view_cart"); 
		response.sendRedirect(cartPage);
		
		
		
	
	}

}
