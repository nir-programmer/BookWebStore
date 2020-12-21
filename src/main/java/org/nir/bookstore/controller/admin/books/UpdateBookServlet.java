package org.nir.bookstore.controller.admin.books;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.BookService;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/admin/update_book")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		BookService bookService = new BookService(request, response); 
		bookService.updateBook(); 
	}

}
