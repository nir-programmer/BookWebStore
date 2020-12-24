package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.BookService;

/**
 * Servlet implementation class ViewBooksByCategoryServlet
 */
@WebServlet("/view_category")
public class ViewBooksByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewBooksByCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		BookService bookService = new BookService(request, response);
		bookService.listBooksByCategory(); 
	}

}
