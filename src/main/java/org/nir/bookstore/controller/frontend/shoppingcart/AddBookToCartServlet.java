package org.nir.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.entities.Book;

@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public AddBookToCartServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer bookId;
		BookDAO bookDAO;
		Book book;
		/*
		 * important: I set this object to the value of the "cart" attribute in the
		 * session and then check if it is null or not...
		 */
		Object cartObject;
		ShoppingCart shoppingCart;
		String cartPage;

		// fetch the book_id query parameter from the request
		bookId = Integer.parseInt(request.getParameter("book_id"));

		// get the Book from the db by the bookDAO
		bookDAO = new BookDAO();
		bookDAO.openCurrentSession();
		book = bookDAO.get(bookId);
		bookDAO.closeCurrentSession();

		System.out.println(
				">>AddBookToCartServlet.doGet():The book's title with id = " + bookId + " is " + book.getTitle());

		/*
		 * WRONG!!! I NEED TO GET THE SHOPPING CART FROM THE SESSION!!!! //add the book
		 * to the cart by using the shoppingCart object shoppingCart = new
		 * ShoppingCart(); shoppingCart.addItem(book);
		 */

		cartObject = request.getSession().getAttribute("cart");

		// if there is session's attribute with attribute named "cart": put it in the
		// shoppingCart
		if (cartObject != null && cartObject instanceof ShoppingCart)
			shoppingCart = (ShoppingCart) cartObject;

		/*
		 * if there is NOT session's attribute with attribute named "cart": create a new
		 * ShoppingCart . add it to the session
		 */

		else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}

		// add the Book to the Shopping Cart
		shoppingCart.addItem(book);

		System.out.println(">>AddBookToCartServlet.doGet():number of books in the cart: = "
		+ shoppingCart.getTotalQuantity());

		/*
		 * NOT FORWARD!!!forward the page
		 * I WANT TO REDIRECT TO THE SHOPPING CART PAGE WITH ITS OWN URL
		 */
		cartPage = request.getContextPath().concat("/view_cart"); 
		response.sendRedirect(cartPage);
	}

}
