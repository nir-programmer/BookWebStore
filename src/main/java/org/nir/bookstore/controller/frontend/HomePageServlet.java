package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.service.CategoriesService;


@WebServlet("")
public class HomePageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		BookDAO bookDAO; 
		String targetPage; 
		List<Book> newBooks;
		List<Book> bestSellingBooks;
		List<Book> mostFavoredBooks;
		
		//Open current session
		bookDAO = new BookDAO(); 
		
		System.out.println(">>HomePageServlet.doGet(): Calling bookDAO.openCurrentSession()...");
		bookDAO.openCurrentSession();
		System.out.println(">>HomePageServlet.doGet():bookDAO.openCurrentSession() RETURNED!");
		
		//Read list of newest books
		newBooks = bookDAO.listNewBooks();
		request.setAttribute("newBooks", newBooks);
		
		
		//Read list of best selling books
		bestSellingBooks = bookDAO.listBestSellingBooks();
		request.setAttribute("bestSellingBooks", bestSellingBooks);
		
		
		//Read list of most favored books
		
		
		//close current session
		System.out.println(">>HomePageServlet.doGet(): Calling bookDAO.closeCurrentSession()...");
		bookDAO.closeCurrentSession();
		System.out.println(">>HomePageServlet.doGet():bookDAO.closeCurrentSession() RETURNED!");
		
		
		//forward to the home page
		targetPage = "frontend/index.jsp"; 
		request.getRequestDispatcher(targetPage).forward(request, response);
		
		//Read list of newest books
		/*
		 * BookDAO bookDAO = new BookDAO();
		 * 
		 * bookDAO.openCurrentSession(); List<Book> newBooks = bookDAO.listNewBooks();
		 * bookDAO.closeCurrentSession();
		 * 
		 * request.setAttribute("newBooks", newBooks);
		 * 
		 * 
		 * 
		 * request.getRequestDispatcher("frontend/index.jsp").forward(request,
		 * response);
		 */
	}

	

}
